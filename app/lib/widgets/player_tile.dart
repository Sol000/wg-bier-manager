import 'package:flutter/material.dart';
import '../models/player.dart';

class PlayerTile extends StatefulWidget {
  final Player player;

  PlayerTile({required this.player});

  @override
  _PlayerTileState createState() => _PlayerTileState();
}

class _PlayerTileState extends State<PlayerTile> {
  void _incrementBeer() {
    setState(() {
      widget.player.beersCount++;
    });
  }

  void _decrementBeer() {
    setState(() {
      if (widget.player.beersCount > 0) {
        widget.player.beersCount--;
      }
    });
  }

  void _showPlayerDetails() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          shape: RoundedRectangleBorder(
            borderRadius:
                BorderRadius.circular(20.0), // Rounded corners for the dialog
          ),
          backgroundColor: Colors.white,
          title: Text(
            widget.player.name,
            style: const TextStyle(
              fontSize: 24.0,
              fontWeight: FontWeight.bold,
              color: Colors.black,
            ),
          ),
          content: SingleChildScrollView(
            child: ListBody(
              children: <Widget>[
                // Example detail text
                Text(
                  'Beers Count: ${widget.player.beersCount}',
                  style: const TextStyle(
                    fontSize: 18.0,
                    fontWeight: FontWeight.normal,
                    color: Color.fromARGB(255, 86, 86, 86),
                  ),
                ),
                // Add more player details here
              ],
            ),
          ),
          actions: <Widget>[
            TextButton(
              child: const Text(
                'Close',
                style: TextStyle(
                  color: Colors.black,
                ),
              ),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 4,
      color: Color.fromARGB(255, 0, 0, 0),
      margin: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
      child: ListTile(
        leading: GestureDetector(
          onTap: _showPlayerDetails,
          child: CircleAvatar(
            backgroundImage: NetworkImage(widget.player.imageUrl),
          ),
        ),
        title: GestureDetector(
          onTap: _showPlayerDetails,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(widget.player.name,
                  style: const TextStyle(
                    color: Colors.black,
                    fontFamily: 'Montserrat',
                    fontSize: 24,
                    fontStyle: FontStyle.normal,
                    fontWeight: FontWeight.normal,
                  )),
              const Text('Player Title',
                  style: TextStyle(
                    color: Colors.grey,
                    fontSize: 14,
                  )),
            ],
          ),
        ),
        trailing: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            IconButton(
              alignment: Alignment.center,
              icon: const Icon(Icons.remove),
              onPressed: _decrementBeer,
            ),
            Text('${widget.player.beersCount}',
                textAlign: TextAlign.center,
                style: const TextStyle(
                  color: Colors.grey,
                  fontSize: 18,
                )),
            IconButton(
              icon: const Icon(Icons.add),
              onPressed: _incrementBeer,
            ),
          ],
        ),
      ),
    );
  }
}
