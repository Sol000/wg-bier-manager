import 'package:flutter/material.dart';
import '../models/player.dart';
import '../widgets/player_tile.dart';

class HomeScreen extends StatelessWidget {
  final List<Player> players = [
    Player(
        name: 'Tippi',
        imageUrl:
            'https://assetsio.reedpopcdn.com/Luffy-Gear-5-eyes-and-smiling.jpg?width=1200&height=1200&fit=bounds&quality=70&format=jpg&auto=webp'),
    Player(name: 'Sammy'),
    // Add more players here
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor:
          Color.fromARGB(255, 20, 20, 20), // Light beer color background
      body: Column(
        children: <Widget>[
          // Image from assets
          Image.asset(
            'assets/Beerallergy-c3fac7368e624d8fa4ab554070d358f8.jpeg', // Replace with your image asset path
            fit: BoxFit.cover,
            width: MediaQuery.of(context).size.width,
            height: 200, // Adjust the height as needed
          ),

          // AppBar-like widget
          Container(
            color: Colors.amberAccent,
            padding: const EdgeInsets.all(10.0),
            child: const Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Text(
                  'BEER TRACKER',
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: 24,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                // Other AppBar widgets if needed
              ],
            ),
          ),

          // The rest of your content
          Expanded(
            child: ListView.builder(
              padding: const EdgeInsets.only(top: 16),
              itemCount: players.length,
              itemBuilder: (context, index) {
                return Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 8.0),
                  child: PlayerTile(player: players[index]),
                );
              },
            ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Add functionality to add new players
        },
        backgroundColor: Colors.amberAccent[200],
        child: const Icon(Icons.add),
      ),
    );
  }
}
