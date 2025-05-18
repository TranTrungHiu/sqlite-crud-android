# Ứng dụng Quản lý Người dùng với SQLite

Ứng dụng Android đơn giản giúp quản lý thông tin người dùng sử dụng SQLite làm cơ sở dữ liệu. Ứng dụng này có các chức năng cơ bản như thêm, sửa, xóa và xem thông tin người dùng (CRUD - Create, Read, Update, Delete).

## Mục lục

- [Yêu cầu hệ thống](#yêu-cầu-hệ-thống)
- [Cài đặt và thiết lập](#cài-đặt-và-thiết-lập)
- [Thư viện sử dụng](#thư-viện-sử-dụng)
- [Kiến trúc và thành phần](#kiến-trúc-và-thành-phần)
  - [Thành phần Backend](#thành-phần-backend)
  - [Thành phần Frontend](#thành-phần-frontend)
- [Luồng hoạt động](#luồng-hoạt-động)
- [Các thao tác chính](#các-thao-tác-chính)
- [Quá trình phát triển](#quá-trình-phát-triển)
- [Một số lưu ý](#một-số-lưu-ý)

## Yêu cầu hệ thống

- Android Studio (phiên bản mới nhất)
- JDK 8 trở lên
- Thiết bị hoặc máy ảo Android với API level 21 trở lên (Android 5.0 Lollipop)
- Gradle 7.0 trở lên

## Cài đặt và thiết lập

1. **Clone dự án từ GitHub:**

   ```bash
   git clone https://github.com/your-username/sqlite-crud-android.git
   ```

2. **Mở dự án trong Android Studio:**

   - Mở Android Studio
   - Chọn "Open an existing project"
   - Điều hướng đến thư mục dự án và chọn OK

3. **Cấu hình Gradle:**

   - Dự án sẽ tự động đồng bộ hóa với Gradle
   - Đảm bảo rằng bạn đã kết nối internet để tải xuống các thư viện cần thiết

4. **Chạy ứng dụng:**
   - Kết nối thiết bị Android hoặc sử dụng máy ảo
   - Nhấp vào nút "Run" (Shift+F10) trong Android Studio

## Thư viện sử dụng

1. **Thư viện Android cốt lõi:**

   - `androidx.appcompat:appcompat`: Cung cấp các tính năng hỗ trợ cho phiên bản Android cũ hơn
   - `androidx.core:core-ktx`: Cung cấp các tiện ích Kotlin cho Android
   - `androidx.recyclerview:recyclerview`: Hiển thị danh sách dữ liệu hiệu quả hơn ListView
   - `androidx.coordinatorlayout:coordinatorlayout`: Quản lý tương tác giữa các component UI khác nhau

2. **Material Design:**

   - `com.google.android.material:material`: Thành phần và kiểu dáng Material Design
   - Các component như MaterialCardView, MaterialButton, TextInputLayout được sử dụng để tạo giao diện hiện đại

3. **SQLite Database:**
   - Sử dụng thư viện SQLite tích hợp sẵn trong Android (android.database.sqlite)

## Kiến trúc và thành phần

### Thành phần Backend

1. **Mô hình dữ liệu (Models):**

   - `User.java`: Lớp POJO (Plain Old Java Object) đại diện cho một người dùng với các thuộc tính: id, username, phone, email
   - Lớp này đã được triển khai để hỗ trợ Serializable giúp truyền dữ liệu giữa các Activity

2. **Bộ điều hợp cơ sở dữ liệu (Database Adapter):**

   - `UsersDatabaseAdapter.java`: Lớp chính quản lý tất cả các tương tác với SQLite
   - Cung cấp các phương thức:
     - `insertEntry()`: Thêm một người dùng mới vào cơ sở dữ liệu
     - `getRows()`: Lấy tất cả người dùng từ cơ sở dữ liệu
     - `updateEntry()`: Cập nhật thông tin người dùng
     - `deleteEntry()`: Xóa một người dùng từ cơ sở dữ liệu
     - `deleteAll()`: Xóa tất cả người dùng
     - `getCount()`: Đếm số lượng người dùng

3. **Lớp SQLiteOpenHelper:**
   - `DatabaseHelper` (lớp con trong `UsersDatabaseAdapter`): Quản lý tạo và nâng cấp cơ sở dữ liệu
   - Cấu trúc bảng: `USERS (id, user_name, phone, email)`

### Thành phần Frontend

1. **Activity chính:**

   - `MainActivity.java`: Điểm vào chính của ứng dụng
   - Hiển thị các nút chính để điều hướng đến các chức năng: Thêm, Sửa, Xóa, Đếm người dùng

2. **Activity chức năng:**

   - `InsertRowActivity.java`: Giao diện thêm người dùng mới
   - `UpdateRowView.java`: Giao diện hiển thị danh sách người dùng để cập nhật
   - `EditUserActivity.java`: Giao diện chỉnh sửa thông tin người dùng
   - `DeleteRowActivity.java`: Giao diện hiển thị danh sách người dùng để xóa

3. **Bộ điều hợp RecyclerView:**

   - `CustomListAdapterUpdateRows.java`: Bộ điều hợp cho RecyclerView trong màn hình cập nhật
   - `CustomListAdapterDeleteRows.java`: Bộ điều hợp cho RecyclerView trong màn hình xóa
   - Các lớp này triển khai ViewHolder Pattern để hiển thị danh sách người dùng hiệu quả

4. **Bố cục (Layouts):**
   - `activity_main.xml`: Bố cục màn hình chính
   - `activity_insert_row.xml`: Bố cục thêm người dùng
   - `activity_update_row.xml`: Bố cục hiển thị danh sách người dùng để cập nhật
   - `activity_edit_user.xml`: Bố cục chỉnh sửa thông tin người dùng
   - `activity_delete_row.xml`: Bố cục hiển thị danh sách người dùng để xóa
   - `list_item_update.xml`: Bố cục cho mỗi item trong danh sách cập nhật
   - `list_item_delete.xml`: Bố cục cho mỗi item trong danh sách xóa

## Luồng hoạt động

1. **Khởi động ứng dụng:**

   - MainActivity được hiển thị với các nút chức năng và số lượng người dùng hiện tại

2. **Thêm người dùng:**

   - Nhấp vào nút "Thêm người dùng" -> Mở InsertRowActivity
   - Nhập thông tin người dùng -> Nhấp nút "Lưu"
   - Dữ liệu được lưu vào SQLite qua UsersDatabaseAdapter

3. **Cập nhật người dùng:**

   - Nhấp vào nút "Cập nhật người dùng" -> Mở UpdateRowView
   - Hiển thị danh sách người dùng với nút "Sửa" cho mỗi người dùng
   - Chọn người dùng cần cập nhật -> Mở EditUserActivity
   - Cập nhật thông tin -> Nhấp nút "Cập nhật"

4. **Xóa người dùng:**

   - Nhấp vào nút "Xóa người dùng" -> Mở DeleteRowActivity
   - Hiển thị danh sách người dùng với nút "Xóa" cho mỗi người dùng
   - Nhấp vào nút "Xóa" -> Hiện hộp thoại xác nhận -> Xác nhận xóa

5. **Đếm người dùng:**

   - Nhấp vào nút "Đếm người dùng" -> Cập nhật hiển thị số lượng người dùng

6. **Xóa tất cả:**
   - Nhấp vào nút "Xóa tất cả" -> Hiện hộp thoại xác nhận -> Xác nhận xóa tất cả người dùng

## Các thao tác chính

### Thêm người dùng mới

```java
// Lấy dữ liệu từ form
String name = nameEditText.getText().toString();
String phone = phoneEditText.getText().toString();
String email = emailEditText.getText().toString();

// Thêm vào cơ sở dữ liệu
UsersDatabaseAdapter dbAdapter = new UsersDatabaseAdapter(this);
dbAdapter.insertEntry(name, phone, email);
```

### Đọc danh sách người dùng

```java
// Lấy danh sách người dùng
UsersDatabaseAdapter dbAdapter = new UsersDatabaseAdapter(this);
ArrayList<User> users = dbAdapter.getRows();
```

### Cập nhật thông tin người dùng

```java
// Cập nhật thông tin
user.setUsername(username);
user.setPhone(phone);
user.setEmail(email);

// Lưu vào cơ sở dữ liệu
UsersDatabaseAdapter dbAdapter = new UsersDatabaseAdapter(this);
dbAdapter.updateEntry(user);
```

### Xóa người dùng

```java
// Xóa người dùng
UsersDatabaseAdapter dbAdapter = new UsersDatabaseAdapter(this);
dbAdapter.deleteEntry(String.valueOf(user.getId()));
```

## Quá trình phát triển

1. **Thiết lập dự án:**

   - Tạo dự án Android mới
   - Cấu hình Gradle và thêm các thư viện cần thiết
   - Thiết lập cấu trúc thư mục

2. **Xây dựng cơ sở dữ liệu:**

   - Tạo lớp model User
   - Triển khai UsersDatabaseAdapter và DatabaseHelper
   - Kiểm thử các thao tác CRUD cơ bản

3. **Phát triển giao diện người dùng:**

   - Thiết kế layout cho các màn hình
   - Triển khai Activity cho từng chức năng
   - Áp dụng Material Design cho giao diện

4. **Nâng cấp từ ListView sang RecyclerView:**

   - Thay thế ListView bằng RecyclerView hiệu quả hơn
   - Tạo và triển khai các Adapter cho RecyclerView
   - Điều chỉnh các Activity để hoạt động với RecyclerView

5. **Kiểm thử và sửa lỗi:**
   - Kiểm tra toàn diện các chức năng
   - Sửa lỗi khi chuyển từ ListView sang RecyclerView
   - Tối ưu hiệu suất và trải nghiệm người dùng

## Một số lưu ý

1. **Quản lý kết nối cơ sở dữ liệu:**

   - Đảm bảo đóng kết nối sau khi sử dụng để tránh rò rỉ bộ nhớ
   - Hiện tại, các phương thức của UsersDatabaseAdapter tự mở và không cần đóng kết nối một cách rõ ràng

2. **Xử lý lỗi:**

   - Thêm xử lý lỗi và kiểm tra dữ liệu đầu vào
   - Hiển thị thông báo phù hợp khi có lỗi xảy ra

3. **Cải thiện trong tương lai:**
   - Thêm chức năng tìm kiếm người dùng
   - Thêm chức năng sắp xếp danh sách
   - Cải thiện giao diện người dùng
   - Áp dụng kiến trúc MVVM hoặc MVP để tách biệt logic hơn nữa
   - Sử dụng Room Database thay vì SQLite trực tiếp
