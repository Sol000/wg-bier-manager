class Player {
  String name;
  String imageUrl;
  int beersCount;

    Player({
        required this.name, 
        this.imageUrl = 'https://t4.ftcdn.net/jpg/02/15/84/43/360_F_215844325_ttX9YiIIyeaR7Ne6EaLLjMAmy4GvPC69.jpg',
        this.beersCount = 0,
    });
}