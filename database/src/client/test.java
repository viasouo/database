package client;

import java.sql.*;
import java.util.Scanner;

public class test {
	private static Connection connection;

	// 建立資料庫連接
	public static void main(String[] args) {
		Connection connection = null;
		String url = "jdbc:mariadb://140.127.74.226:3306/411077020";
		String user = "411077020";
		String password = "411077020";

		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("連接到資料庫成功\n");

			Scanner input = new Scanner(System.in);
			System.out.println("輸入 【0】 查詢過去一年中按價格購買金額最多的客戶");
			System.out.println("輸入 【1】 查找高雄的所有店鋪都缺貨的產品");
			System.out.println("輸入 【2】 查找未按時交付的套裝產品");
			System.out.println("輸入 【3】 查找過去一年中按銷售金額排名前2的產品");
			System.out.println("若包裹損壞請輸入 【4】 查找客戶的聯繫信息。同時查找該包裹的內容");
			String line = input.nextLine();

			String query;
			Statement statement;
			ResultSet resultSet;

			switch (line) {
			case "0":
				// 查詢過去一年中按價格購買金額最多的客戶
				query = "SELECT o.CustomerID, SUM(p.ProductPrice) AS TotalPurchaseAmount " + "FROM OrderForm o "
						+ "JOIN Product p ON o.ProductID = p.ProductID "
						+ "WHERE o.OrderTime >= DATE_SUB(NOW(), INTERVAL 1 YEAR) " + "GROUP BY o.CustomerID "
						+ "ORDER BY TotalPurchaseAmount DESC " + "LIMIT 1";

				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);

				if (resultSet.next()) {
					int customerID = resultSet.getInt("CustomerID");
					double totalPurchaseAmount = resultSet.getDouble("TotalPurchaseAmount");
					System.out.println("-------------------------");
					System.out.println("過去一年中按價格購買金額最多的客戶是：" + customerID);
					System.out.println("購買金額總計：" + totalPurchaseAmount);
					System.out.println("-------------------------");
				}
				break;

			case "1":
				// 查找高雄的所有店鋪都缺貨的產品。(庫存、店鋪)
				query = "SELECT i.ProductID, p.ProductSetName " + "FROM Inventory i "
						+ "JOIN Product p ON i.ProductID = p.ProductID "
						+ "WHERE i.StoreID != '高雄' AND i.ProductQuantity = 0";

				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);

				System.out.println("在高雄所有商店中缺貨的產品:");
				while (resultSet.next()) {
					int productID = resultSet.getInt("ProductID");
					String productSetName = resultSet.getString("ProductSetName");
					System.out.println("-------------------------");
					System.out.println("產品ID: " + productID);
					System.out.println("產品名稱: " + productSetName);
					System.out.println("-------------------------");
				}

				break;

			// 查找未按時交付的套裝產品
			case "2":

				query = "SELECT OrderForm.ProductID, Product.ProductSetName FROM DeliveryCompany "
						+ "JOIN OrderForm ON DeliveryCompany.TrackingNumber = OrderForm.TrackingNumber "
						+ "JOIN Product ON OrderForm.ProductID = Product.ProductID "
						+ "WHERE DeliveryCompany.Finish = '未交付' AND Product.ProductType = '套裝'";
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);

				// 打印查询结果
				while (resultSet.next()) {
					int productId = resultSet.getInt("ProductID");
					String productSetName = resultSet.getString("ProductSetName");
					System.out.println("-------------------------");
					System.out.println("未按時交付的套裝產品:");
					System.out.println("ProductSetName: " + productSetName);
					System.out.println("-------------------------");
				}
				break;

			case "3":
				// 過去一年中按銷售金額排名第二的產品：
				query = "SELECT p.ProductID, p.ProductSetName, SUM(o.OrderQuantity) AS totalSalesQuantity, "
						+ "SUM(o.OrderQuantity * p.ProductPrice) AS totalSalesAmount " + "FROM OrderForm o "
						+ "JOIN Product p ON o.ProductID = p.ProductID "
						+ "WHERE o.OrderTime >= DATE_SUB(NOW(), INTERVAL 1 YEAR) "
						+ "GROUP BY p.ProductID, p.ProductSetName " + "ORDER BY totalSalesQuantity DESC " + "LIMIT 2";

				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);

				System.out.println("過去一年中按銷售金額排名第二的產品：");
				while (resultSet.next()) {
					int productID = resultSet.getInt("ProductID");
					String productSetName = resultSet.getString("ProductSetName");
					int totalSalesQuantity = resultSet.getInt("totalSalesQuantity");
					double totalSalesAmount = resultSet.getDouble("totalSalesAmount");

					System.out.println("產品ID：" + productID);
					System.out.println("產品名稱：" + productSetName);
					System.out.println("銷售數量：" + totalSalesQuantity);
					System.out.println("總銷售額：" + totalSalesAmount);
					System.out.println("-------------------------");

				}
				break;

			case "4":
				System.out.println("-------------------------");
				System.out.print("請輸入單號:");
				int trackingNumber = input.nextInt();
				System.out.println("-------------------------");

				// 查詢OrderForm表格中符合條件的資料
				query = "SELECT OrderForm.TrackingNumber, OrderForm.ProductID, Customer.Name, OrderForm.ContactInfo "
						+ "FROM OrderForm " + "INNER JOIN Customer ON OrderForm.CustomerID = Customer.CustomerID "
						+ "WHERE OrderForm.TrackingNumber = ?";

				PreparedStatement statement1 = connection.prepareStatement(query);
				statement1.setInt(1, trackingNumber);
				ResultSet resultSet1 = statement1.executeQuery();

				if (resultSet1.next()) {
					int resultTrackingNumber = resultSet1.getInt("TrackingNumber");
					int productID = resultSet1.getInt("ProductID");
					String name = resultSet1.getString("Name");
					String contactInfo = resultSet1.getString("ContactInfo");

					// 列印結果
					System.out.println("追蹤單號：" + resultTrackingNumber);
					System.out.println("客戶姓名：" + name);
					System.out.println("聯絡方式：" + contactInfo);
					System.out.println("-------------------------");
					int product = productID;

					query = "SELECT ProductID, ProductSetName, ProductPrice, ProductType, Manufacturer "
							+ "FROM Product " + "WHERE ProductID = ?";

					PreparedStatement statement2 = connection.prepareStatement(query);
					statement2.setInt(1, product);
					ResultSet resultSet2 = statement2.executeQuery();

					if (resultSet2.next()) {
						int resultProductID = resultSet2.getInt("ProductID");
						String productSetName = resultSet2.getString("ProductSetName");
						double productPrice = resultSet2.getDouble("ProductPrice");
						String productType = resultSet2.getString("ProductType");
						String manufacturer = resultSet2.getString("Manufacturer");

						// 列印結果
						System.out.println("包裹內容");
						System.out.println("商品名稱：" + productSetName);
						System.out.println("商品價錢：" + productPrice);
						System.out.println("商品種類：" + productType);
						System.out.println("製造商：" + manufacturer);
						System.out.println("-------------------------");
					}
					
				} else {
					System.out.println("-------------------------");
					System.out.println("找不到符合條件的資料。");
					System.out.println("-------------------------");
				}
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
