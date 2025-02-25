package com.example.lpg.android.data.remote.api

import com.example.lpg.android.data.model.GasItem
import com.google.gson.Gson
import java.util.UUID

object MockDataProvider {

    //30 randomized gases
    val mockData = """
    [
        { "id": "1", "name": "3kg", "price": 2415.00, "currency": "KES", "image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMNFVM7k83daD7r9DC8PG89srPGmvcwtz5fg&s" },
        { "id": "2", "name": "6kg", "price": 3190.00, "currency": "KES", "image_url": "https://example.com/images/6kg-cylinder.png" },
        { "id": "3", "name": "13kg", "price": 6590.00, "currency": "KES", "image_url": "https://example.com/images/13kg-cylinder.png" },
        { "id": "4", "name": "22.5kg", "price": 13170.00, "currency": "KES", "image_url": "https://example.com/images/13kg-cylinder.png" },
        { "id": "5", "name": "6kg", "price": 3190.00, "currency": "KES", "image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA1bzwjqht3bNNdWBKNB-0JsaJ5_x1Yxz0AA&s" },
        { "id": "6", "name": "3kg", "price": 2415.00, "currency": "KES", "image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMNFVM7k83daD7r9DC8PG89srPGmvcwtz5fg&s" },
        { "id": "7", "name": "13kg", "price": 6590.00, "currency": "KES", "image_url": "https://example.com/images/13kg-cylinder.png" },
        { "id": "8", "name": "22.5kg", "price": 13170.00, "currency": "KES", "image_url": "https://example.com/images/13kg-cylinder.png" },
        { "id": "9", "name": "3kg", "price": 2415.00, "currency": "KES", "image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMNFVM7k83daD7r9DC8PG89srPGmvcwtz5fg&s" },
        { "id": "10", "name": "6kg", "price": 3190.00, "currency": "KES", "image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA1bzwjqht3bNNdWBKNB-0JsaJ5_x1Yxz0AA&s" },
        { "id": "11", "name": "13kg", "price": 6590.00, "currency": "KES", "image_url": "https://example.com/images/13kg-cylinder.png" },
        { "id": "12", "name": "22.5kg", "price": 13170.00, "currency": "KES", "image_url": "https://example.com/images/13kg-cylinder.png" },
        { "id": "13", "name": "6kg", "price": 3190.00, "currency": "KES", "image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA1bzwjqht3bNNdWBKNB-0JsaJ5_x1Yxz0AA&s" },
        { "id": "14", "name": "3kg", "price": 2415.00, "currency": "KES", "image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMNFVM7k83daD7r9DC8PG89srPGmvcwtz5fg&s" },
        { "id": "15", "name": "13kg", "price": 6590.00, "currency": "KES", "image_url": "https://example.com/images/13kg-cylinder.png" },
        { "id": "16", "name": "22.5kg", "price": 13170.00, "currency": "KES", "image_url": "https://example.com/images/13kg-cylinder.png" },
        { "id": "17", "name": "3kg", "price": 2415.00, "currency": "KES", "image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMNFVM7k83daD7r9DC8PG89srPGmvcwtz5fg&s" },
        { "id": "18", "name": "6kg", "price": 3190.00, "currency": "KES", "image_url": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA1bzwjqht3bNNdWBKNB-0JsaJ5_x1Yxz0AA&s" },
        { "id": "19", "name": "13kg", "price": 6590.00, "currency": "KES", "image_url": "https://onestopwholesalers.co.ke/wp-content/uploads/2024/06/GS8.png" },
        { "id": "20", "name": "22.5kg", "price": 13170.00, "currency": "KES", "image_url": "https://onestopwholesalers.co.ke/wp-content/uploads/2024/06/GS8.png" },
        { "id": "21", "name": "6kg", "price": 3190.00, "currency": "KES", "image_url": "https://example.com/images/6kg-cylinder.png" },
        { "id": "22", "name": "3kg", "price": 2415.00, "currency": "KES", "image_url": "https://example.com/images/3kg-cylinder.png" },
        { "id": "23", "name": "13kg", "price": 6590.00, "currency": "KES", "image_url": "https://onestopwholesalers.co.ke/wp-content/uploads/2024/06/GS8.png" },
        { "id": "24", "name": "22.5kg", "price": 13170.00, "currency": "KES", "image_url": "https://onestopwholesalers.co.ke/wp-content/uploads/2024/06/GS8.png" },
        { "id": "25", "name": "3kg", "price": 2415.00, "currency": "KES", "image_url": "https://example.com/images/3kg-cylinder.png" },
        { "id": "26", "name": "6kg", "price": 3190.00, "currency": "KES", "image_url": "https://example.com/images/6kg-cylinder.png" },
        { "id": "27", "name": "13kg", "price": 6590.00, "currency": "KES", "image_url": "https://onestopwholesalers.co.ke/wp-content/uploads/2024/06/GS8.png" },
        { "id": "28", "name": "22.5kg", "price": 13170.00, "currency": "KES", "image_url": "https://example.com/images/13kg-cylinder.png" },
        { "id": "29", "name": "6kg", "price": 3190.00, "currency": "KES", "image_url": "https://example.com/images/6kg-cylinder.png" },
        { "id": "30", "name": "3kg", "price": 2415.00, "currency": "KES", "image_url": "https://example.com/images/3kg-cylinder.png" }
    ]
""".trimIndent()

    private fun generateRandomGasItems(count: Int = 30): List<GasItem> {

        val cylinderTypes = listOf(
            GasItem(
                id = UUID.randomUUID().toString(),
                name = "3kg",
                price = 2415.00,
                currency = "KES",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/apptales-6c0f3.appspot.com/o/valone%2F3kggas.jpeg?alt=media&token=deb9da01-93af-40b6-9d9c-19d086953b89"
            ),
            GasItem(
                id = UUID.randomUUID().toString(),
                name = "6kg",
                price = 3190.00,
                currency = "KES",
                imageUrl =
                "https://firebasestorage.googleapis.com/v0/b/apptales-6c0f3.appspot.com/o/valone%2F6kggas.jpeg?alt=media&token=79779a7b-6b8a-4fd1-a480-4b984eb5db00"
            ),
            GasItem(
                id = UUID.randomUUID().toString(),
                name = "13kg",
                price = 6590.00,
                currency = "KES",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/apptales-6c0f3.appspot.com/o/valone%2F13kggas.png?alt=media&token=27a4792b-0ec8-4e94-9a49-d5ee0da5741e"
            ),
            GasItem(
                id = UUID.randomUUID().toString(),
                name = "22.5kg",
                price = 13170.00,
                currency = "KES",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/apptales-6c0f3.appspot.com/o/valone%2F22.5KGgas.png?alt=media&token=57cbe092-9b17-4d99-bd68-5a20cd4ecb11"
            )
        )

        return List(count) {
            val randomItem = cylinderTypes.random()
            randomItem.copy(id = UUID.randomUUID().toString())  // Assign a new unique ID
        }
    }

    val producedData: String by lazy {
        Gson().toJson(generateRandomGasItems())
    }

    val baseUrl = "https://www.lpg.example.com/"

}

