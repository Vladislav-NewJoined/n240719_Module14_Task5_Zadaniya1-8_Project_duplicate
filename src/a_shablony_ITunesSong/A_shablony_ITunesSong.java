package a_shablony_ITunesSong;

/*public */class Task1_4_1_0_iTunesProduct {
    String artistName;
    String trackName;
    String collectionName;
    String previewUrl;
    String country;
    String releaseDate;


    void printIntroducePreview() {
        System.out.println(this.artistName + " - " + this.collectionName);
        System.out.println("url to preview: " + " - " + this.previewUrl);
    }

    void printReleaseDate() {
        System.out.println(collectionName + " has released " + this.releaseDate);
    }
}

public class A_shablony_ITunesSong  extends Task1_4_1_0_iTunesProduct {
    public static void main(String[] args) {

        Task1_4_1_0_iTunesProduct iTunesProduct = new Task1_4_1_0_iTunesProduct();
        iTunesProduct.printIntroducePreview();
        iTunesProduct.printReleaseDate();

        // common
        String artistName;
        String collectionName;
        String previewUrl;

        String trackName;
        String trackCensoredName;
        //    double trackPrice;
        String trackPrice;
        //    double collectionPrice;
        String collectionPrice;
        }

//    void printIntroducePreview() {
//        System.out.println(this.artistName + " - " + this.collectionName);
//        System.out.println("url to preview: " + " - " + this.previewUrl);
//    }
    // /common
}

