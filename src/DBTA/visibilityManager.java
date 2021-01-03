package DBTA;

public class visibilityManager {

    mainUI mUI;

    public visibilityManager(mainUI theUI){
        mUI = theUI;
    }

    public void visibilityAtStart() {
        // Show
        mUI.loginMenu.setVisible(true);

        // Hide
        mUI.registerPanel.setVisible(false);
        mUI.movieMenu.setVisible(false);
        mUI.ratingMenu.setVisible(false);
        mUI.insideRatingMenu.setVisible(false);
    }

    public void visibilityAtRegister() {
        // Show
        mUI.registerPanel.setVisible(true);

        // Hide
        mUI.loginMenu.setVisible(false);
        mUI.movieMenu.setVisible(false);
        mUI.ratingMenu.setVisible(false);
        mUI.insideRatingMenu.setVisible(false);
    }

    public void visibilityOnMovie() {
        // Show
        mUI.movieMenu.setVisible(true);

        // Hide
        mUI.registerPanel.setVisible(false);
        mUI.loginMenu.setVisible(false);
        mUI.ratingMenu.setVisible(false);
        mUI.insideRatingMenu.setVisible(false);
    }

    public void visibilityAtInsideUserReview(){
        // Show
        mUI.insideRatingMenu.setVisible(true);

        // Hide
        mUI.registerPanel.setVisible(false);
        mUI.loginMenu.setVisible(false);
        mUI.ratingMenu.setVisible(false);
        mUI.movieMenu.setVisible(false);

    }

    public void visibilityAtUserReview(){
        // Show
        mUI.ratingMenu.setVisible(true);

        // Hide
        mUI.registerPanel.setVisible(false);
        mUI.loginMenu.setVisible(false);
        mUI.insideRatingMenu.setVisible(false);
        mUI.movieMenu.setVisible(false);
    }
}
