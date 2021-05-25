package api;

public final class RestWrapper {

  public final BreedsService breeds;
  public final FavouritesService favourites;
  public final ImagesSearchService imagesSearch;

  public RestWrapper() {
    this.breeds = new BreedsService();
    this.favourites = new FavouritesService();
    this.imagesSearch = new ImagesSearchService();
  }
}
