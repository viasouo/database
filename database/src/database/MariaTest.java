package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MariaTest {

	public static void main(String[] args) {
		Connection connection = null;
		String url = "jdbc:mariadb://140.127.74.226:3306/411077020";
		String user = "411077020";
		String password = "411077020";

		try {
			// 連接到資料庫
			connection = DriverManager.getConnection(url, user, password);

			// 建立表格
			createCustomerTable(connection);
			createProductTable(connection);
			createInventoryTable(connection);
			createStoreTable(connection);
			createOrderFormTable(connection);
			createDeliveryCompanyTable(connection);

			// 插入資料---------------------------------------------------------------------------

			// 客戶資料
			// insertCustomerData(connection,客戶編號, 客戶姓名,客戶住家地址, 聯絡方式, 帳戶,支付方式) ;
			insertCustomerData(connection, 1, "王大明", "大明家地址", "0911111111", "大明帳戶", "信用卡");
			insertCustomerData(connection, 2, "王小明", "小明家地址", "0922222222", "小明帳戶", "現金");
			insertCustomerData(connection, 3, "王中明", "中明家地址", "0933333333", "中明帳戶", "信用卡");

			// 產品
			// insertProducData(connection, 商品編號, 商品名稱, 商品價格,商品種類,製造商,銷售量排行 );
			insertProducData(connection, 000, "手機", 10000, "手機", "華碩", 1);
			insertProducData(connection, 001, "電腦螢幕", 5000, "電腦", "華碩", 2);
			insertProducData(connection, 002, "鍵盤", 2000, "電腦", "華碩", 5);
			insertProducData(connection, 003, "滑鼠", 100, "電腦", "華碩", 4);
			insertProducData(connection, 004, "主機", 10000, "電腦", "華碩", 3);
			insertProducData(connection, 005, "充電線", 2000, "其他", "華碩", 6);
			insertProducData(connection, 006, "電腦套裝", 15000, "套裝", "蘋果", 8);
			insertProducData(connection, 007, "手機套裝", 10000, "套裝", "蘋果", 7);

			// 庫存
			// insertInventoryData( connection, 商品編號,商品價格,商品種類,店鋪地區,商品數量,店鋪編號);
			// 快樂店鋪
			insertInventoryData(connection, 000, 10000, "手機", "高雄地區", 4, 1);
			insertInventoryData(connection, 001, 5000, "電腦", "高雄地區", 0, 1);
			insertInventoryData(connection, 002, 2000, "電腦", "高雄地區", 6, 1);
			insertInventoryData(connection, 003, 100, "電腦", "高雄地區", 7, 1);
			insertInventoryData(connection, 004, 10000, "電腦", "高雄區", 8, 1);
			insertInventoryData(connection, 005, 2000, "手機", "高雄地區", 9, 1);
			insertInventoryData(connection, 006, 15000, "套裝", "高雄地區", 5, 1);
			insertInventoryData(connection, 007, 10000, "套裝", "高雄地區", 4, 1);
			// 善良店鋪
			insertInventoryData(connection, 000, 10000, "手機", "高雄地區", 4, 2);
			insertInventoryData(connection, 001, 5000, "電腦", "高雄地區", 0, 2);
			insertInventoryData(connection, 002, 2000, "電腦", "高雄地區", 6, 2);
			insertInventoryData(connection, 003, 100, "電腦", "高雄地區", 7, 2);
			insertInventoryData(connection, 004, 10000, "電腦", "高雄區", 8, 2);
			insertInventoryData(connection, 005, 2000, "手機", "高雄地區", 9, 2);
			insertInventoryData(connection, 006, 15000, "套裝", "高雄地區", 5, 2);
			insertInventoryData(connection, 007, 10000, "套裝", "高雄地區", 4, 2);
			// 可愛店鋪
			insertInventoryData(connection, 000, 10000, "手機", "高雄地區", 4, 3);
			insertInventoryData(connection, 001, 5000, "電腦", "高雄地區", 0, 3);
			insertInventoryData(connection, 002, 2000, "電腦", "高雄地區", 6, 3);
			insertInventoryData(connection, 003, 100, "電腦", "高雄地區", 7, 3);
			insertInventoryData(connection, 004, 10000, "電腦", "高雄區", 8, 3);
			insertInventoryData(connection, 005, 2000, "手機", "高雄地區", 9, 3);
			insertInventoryData(connection, 006, 15000, "套裝", "高雄地區", 5, 3);
			insertInventoryData(connection, 007, 10000, "套裝", "高雄地區", 4, 3);

			// 訂單
			// insertOrderFormData (單號,客戶id,商品號,追蹤號,"連絡方式",訂購數量,時間,);
			insertOrderFormData(connection, 001, 1, 007, 0001, "0911111111", 1, "2023-06-13 13:32:15");
			insertOrderFormData(connection, 002, 2, 005, 0002, "0922222222", 1, "2023-06-12 20:00:15");
			insertOrderFormData(connection, 003, 3, 000, 0003, "0933333333", 1, "2023-06-12 18:30:15");
			insertOrderFormData(connection, 004, 1, 002, 0004, "0911111111", 1, "2023-06-12 18:30:15");
			insertOrderFormData(connection, 005, 1, 006, 0005, "0911111111", 1, "2020-06-12 18:30:15");
			insertOrderFormData(connection, 006, 2, 000, 0006, "0922222222", 1, "2023-06-13 15:30:15");
			insertOrderFormData(connection, 007, 2, 005, 123456, "0922222222", 1, "2023-06-13 15:30:15");

			// 店鋪
			// insertStoreData( 店鋪號, 單號 ,商品數,商品名, 商品號,商品價格,店鋪地區, 地址, 店鋪名稱);
			insertStoreData(connection, 1, 001, 1, "手機套裝", 007, 10000, "高雄地區", "快樂店鋪地址", "快樂店鋪");
			insertStoreData(connection, 2, 002, 1, "充電線", 005, 2000, "高雄地區", "善良店鋪地址", "善良店鋪");
			insertStoreData(connection, 3, 003, 1, "手機", 000, 10000, "高雄地區", "可愛店鋪地址", "可愛店鋪");
			insertStoreData(connection, 1, 004, 1, "鍵盤", 002, 2000, "高雄地區", "快樂店鋪地址", "快樂店鋪");
			insertStoreData(connection, 1, 005, 1, "電腦套裝", 006, 15000, "高雄地區", "快樂店鋪地址", "快樂店鋪");
			insertStoreData(connection, 2, 006, 1, "手機套裝", 007, 10000, "高雄地區", "善良店鋪地址", "善良店鋪");

			// 運送公司
			// insertDeliveryCompanyData( connection, 追蹤編號,公司名稱,單號,尚未交付);
			insertDeliveryCompanyData(connection, 0001, "oo運送公司", 1, "已交付");
			insertDeliveryCompanyData(connection, 0002, "XX運送公司", 2, "未交付");
			insertDeliveryCompanyData(connection, 0003, "oo運送公司", 1, "已交付");
			insertDeliveryCompanyData(connection, 0004, "oo運送公司", 1, "未交付");
			insertDeliveryCompanyData(connection, 0005, "XX運送公司", 2, "未交付");
			insertDeliveryCompanyData(connection, 0006, "oo運送公司", 1, "已交付");
			insertDeliveryCompanyData(connection, 123456, "oo運送公司", 1, "未交付");

			// 查詢資料
			// queryData(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 關閉資料庫連接
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 創建表格
	private static void createCustomerTable(Connection connection) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Customer (CustomerID INT PRIMARY KEY, Name VARCHAR(255), Address VARCHAR(255), ContactInfo VARCHAR(255), AccountNumber VARCHAR(255), PaymentMethod VARCHAR(255))";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();
			System.out.println("Customer table created successfully");
		}
	}

	private static void createProductTable(Connection connection) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Product (ProductID INT PRIMARY KEY, ProductSetName VARCHAR(255), ProductPrice DECIMAL(10,2), ProductType VARCHAR(255), Manufacturer VARCHAR(255), SalesRank INT)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();
			System.out.println("Product table created successfully");
		}
	}

	private static void createInventoryTable(Connection connection) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Inventory (ProductID INT PRIMARY KEY, ProductPrice DECIMAL(10,2), ProductType VARCHAR(255), StoreLocation VARCHAR(255), ProductQuantity INT, StoreID INT)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();
			System.out.println("Inventory table created successfully");
		}
	}

	private static void createStoreTable(Connection connection) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Store (StoreID INT PRIMARY KEY, OrderFormID INT, ProductQuantity INT, ProductSetName VARCHAR(255), ProductID INT, ProductPrice DECIMAL(10,2), StoreLocation VARCHAR(255), Address VARCHAR(255), StoreName VARCHAR(255))";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();
			System.out.println("Store table created successfully");
		}
	}

	private static void createOrderFormTable(Connection connection) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS OrderForm (OrderFormID INT PRIMARY KEY, CustomerID INT, ProductID INT, TrackingNumber INT, ContactInfo VARCHAR(255), OrderQuantity INT, OrderTime VARCHAR(255))";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();
			System.out.println("OrderForm table created successfully");
		}
	}

	private static void createDeliveryCompanyTable(Connection connection) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS DeliveryCompany (TrackingNumber INT PRIMARY KEY, CompanyName VARCHAR(255), CompanyID INT, Finish VARCHAR(255))";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();
			System.out.println("DeliveryCompany table created successfully");
		}
	}

	private static void insertCustomerData(Connection connection, int CustomerID, String Name, String Address,
			String ContactInfo, String AccountNumber, String PaymentMethod) throws SQLException {
		String sql = "INSERT INTO Customer (CustomerID, Name, Address, ContactInfo, AccountNumber, PaymentMethod) VALUES (?, ?, ?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE Name=VALUES(Name), Address=VALUES(Address), ContactInfo=VALUES(ContactInfo), "
				+ "AccountNumber=VALUES(AccountNumber), PaymentMethod=VALUES(PaymentMethod)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, CustomerID);
			statement.setString(2, Name);
			statement.setString(3, Address);
			statement.setString(4, ContactInfo);
			statement.setString(5, AccountNumber);
			statement.setString(6, PaymentMethod);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Data inserted into Customer table successfully");
			}
		}
	}

	private static void insertProducData(Connection connection, int ProductID, String ProductSetName,
			double ProductPrice, String ProductType, String Manufacturer, int SalesRank) throws SQLException {
		String sql = "INSERT INTO Product (ProductID, ProductSetName, ProductPrice, ProductType, Manufacturer, SalesRank) VALUES (?, ?, ?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE ProductSetName=VALUES(ProductSetName), ProductPrice=VALUES(ProductPrice), "
				+ "ProductType=VALUES(ProductType), Manufacturer=VALUES(Manufacturer), SalesRank=VALUES(SalesRank)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, ProductID);
			statement.setString(2, ProductSetName);
			statement.setDouble(3, ProductPrice);
			statement.setString(4, ProductType);
			statement.setString(5, Manufacturer);
			statement.setInt(6, SalesRank);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Data inserted into Product table successfully");
			}
		}
	}

	private static void insertInventoryData(Connection connection, int ProductID, double ProductPrice,
			String ProductType, String StoreLocation, int ProductQuantity, int StoreID) throws SQLException {
		String sql = "INSERT INTO Inventory (ProductID, ProductPrice, ProductType, StoreLocation, ProductQuantity, StoreID) "
				+ "VALUES (?,?,?,?,?,?) " + "ON DUPLICATE KEY UPDATE " + "ProductPrice = VALUES(ProductPrice), "
				+ "ProductType = VALUES(ProductType), " + "StoreLocation = VALUES(StoreLocation), "
				+ "ProductQuantity = VALUES(ProductQuantity), " + "StoreID = VALUES(StoreID)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, ProductID);
			statement.setDouble(2, ProductPrice);
			statement.setString(3, ProductType);
			statement.setString(4, StoreLocation);
			statement.setInt(5, ProductQuantity);
			statement.setInt(6, StoreID);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Data inserted into Inventory table successfully");
			}
		}
	}

	private static void insertStoreData(Connection connection, int StoreID, int OrderFormID, int ProductQuantity,
			String ProductSetName, int ProductID, double ProductPrice, String StoreLocation, String Address,
			String StoreName) throws SQLException {
		String sql = "INSERT INTO Store (StoreID, OrderFormID, ProductQuantity, ProductSetName, ProductID, ProductPrice, StoreLocation, Address, StoreName) "
				+ "VALUES (?,?,?,?,?,?,?,?,?) " + "ON DUPLICATE KEY UPDATE " + "OrderFormID = VALUES(OrderFormID), "
				+ "ProductQuantity = VALUES(ProductQuantity), " + "ProductSetName = VALUES(ProductSetName), "
				+ "ProductID = VALUES(ProductID), " + "ProductPrice = VALUES(ProductPrice), "
				+ "StoreLocation = VALUES(StoreLocation), " + "Address = VALUES(Address), "
				+ "StoreName = VALUES(StoreName)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, StoreID);
			statement.setInt(2, OrderFormID);
			statement.setInt(3, ProductQuantity);
			statement.setString(4, ProductSetName);
			statement.setInt(5, ProductID);
			statement.setDouble(6, ProductPrice);
			statement.setString(7, StoreLocation);
			statement.setString(8, Address);
			statement.setString(9, StoreName);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Data inserted into Store table successfully");
			}
		}
	}

	private static void insertOrderFormData(Connection connection, int OrderFormID, int CustomerID, int ProductID,
			int TrackingNumber, String ContactInfo, int OrderQuantity, String OrderTime) throws SQLException {
		String sql = "INSERT INTO OrderForm (OrderFormID, CustomerID, ProductID, TrackingNumber, ContactInfo, OrderQuantity, OrderTime) "
				+ "VALUES (?,?,?,?,?,?,?) " + "ON DUPLICATE KEY UPDATE " + "CustomerID = VALUES(CustomerID), "
				+ "ProductID = VALUES(ProductID), " + "TrackingNumber = VALUES(TrackingNumber), "
				+ "ContactInfo = VALUES(ContactInfo), " + "OrderQuantity = VALUES(OrderQuantity), "
				+ "OrderTime = VALUES(OrderTime)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, OrderFormID);
			statement.setInt(2, CustomerID);
			statement.setInt(3, ProductID);
			statement.setInt(4, TrackingNumber);
			statement.setString(5, ContactInfo);
			statement.setInt(6, OrderQuantity);
			statement.setString(7, OrderTime);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Data inserted into OrderForm table successfully");
			}
		}
	}

	private static void insertDeliveryCompanyData(Connection connection, int TrackingNumber, String CompanyName,
			int CompanyID, String Finish) throws SQLException {
		String sql = "INSERT INTO DeliveryCompany (TrackingNumber, CompanyName, CompanyID, Finish) "
				+ "VALUES (?,?,?,?) " + "ON DUPLICATE KEY UPDATE CompanyName = VALUES(CompanyName), "
				+ "CompanyID = VALUES(CompanyID), Finish = VALUES(Finish)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, TrackingNumber);
			statement.setString(2, CompanyName);
			statement.setInt(3, CompanyID);
			statement.setString(4, Finish); // Assuming IsFinish is a boolean column and initially set to false

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Data inserted into DeliveryCompany table successfully");
			}
		}
	}

}
