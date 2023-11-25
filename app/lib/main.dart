import 'package:flutter/material.dart';
import 'screens/home_screen.dart';

void main() {
  runApp(BeerApp());
}

class BeerApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Beer App',
      theme: ThemeData(
        primarySwatch: Colors.amber,
      ),
      home: HomeScreen(),
    );
  }
}
