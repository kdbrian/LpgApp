# LPG Gas Cylinder App 🚀
An Android application built with **Jetpack Compose** and **Kotlin**, allowing users to view, select, and purchase LPG gas cylinders of various sizes. The app uses **MVVM Architecture**, **StateFlow**, and **Paging 3** for efficient data management and pagination. It also features a **Mocked API** with dynamic data generation.

---

## Features 🛠️
- **View Available LPG Cylinders**: Displays a list of LPG cylinders (3kg, 6kg, 13kg, and 22.5kg) with images, names, and prices.
- **Add to Cart**: Allows users to select a cylinder and add it to the cart.
- **Checkout Process**: Handles checkout with success and error scenarios.
- **Pagination**: Smooth scrolling with Paging 3.
- **Mocked API**: Dynamically generated data using `MockInterceptor`.
- **Offline Support (Optional)**: Caching data for offline access.
- **Modern UI**: Built with **Jetpack Compose** for a clean and responsive interface.

---

## Tech Stack 🧰
- **Kotlin** - Programming language
- **Jetpack Compose** - Modern UI Toolkit
- **MVVM Architecture** - Clean and maintainable structure
- **StateFlow** - State management
- **Retrofit** - Networking
- **Paging 3** - Infinite scrolling and pagination
- **Coil** - Image loading
- **Kotlin Coroutines** - Asynchronous programming

---

## Screenshots 📸
- Home Screen (Cylinder List)
- Cart Screen
- Checkout Success and Error States

---

## Getting Started 🚀
### Prerequisites:
- **Android Studio Giraffe or later**
- **JDK 11 or later**
- **Gradle 8.0 or later**

### Clone the Repository:
```bash
git clone https://gitlab.com/your-username/lpg-gas-cylinder-app.git
cd lpg-gas-cylinder-app
```
Mocked API and Data Generation 🧪

The app uses a Mocked API with MockInterceptor to simulate backend data using Retrofit. Data is dynamically generated using MockDataProvider.

Example Data:
```json
{
"id": "c6f7e8c4-2d3b-4c6b-82e2-48f8bda03f94",
"name": "6kg Gas Cylinder",
"price": 3190.00,
"currency": "KES",
"image_url": "https://example.com/images/6kg-cylinder.png"
}
```

### UI and State Management 🎨
- Built with Jetpack Compose for declarative UI.
- Uses StateFlow and LazyPagingItems for reactive UI updates.
- Efficient image loading with Coil.