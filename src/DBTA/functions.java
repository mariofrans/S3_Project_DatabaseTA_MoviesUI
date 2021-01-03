package DBTA;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class functions {

    mainUI mUI;
    visibilityManager vm;

    public functions(mainUI theUI, visibilityManager visibilityManager){
        mUI = theUI;
        vm = visibilityManager;
    }

    public void loginCheck() {
        try {
            conDB.connection = DriverManager.getConnection(conDB.url, conDB.user, conDB.password);
            String inside = mUI.userNameTextField.getText();
            String insidePass = mUI.passwordTextField.getText();
            String q = "select * from user where username=" + " '" + inside + "' ";
            String qpass = "select password from user where username=" + " '" + inside + "' ";
            PreparedStatement pst2 = conDB.connection.prepareStatement(qpass);
            conDB.result = pst2.executeQuery();
            if (conDB.result.next()){
                String pass = conDB.result.getString("password");
                if (pass.contains(insidePass)){
                    PreparedStatement pst = conDB.connection.prepareStatement(q);
                    conDB.result = pst.executeQuery();
                    if (conDB.result.next()){
                        JOptionPane.showMessageDialog(null, "Welcome back!");
                        vm.visibilityOnMovie();
                    } else {
                        JOptionPane.showMessageDialog(null, "Your input is incorrect");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Your input is incorrect");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your input is incorrect");
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Your input is incorrect");
        }
    }

    public void toRegister() {
        try {
            conDB.connection = DriverManager.getConnection(conDB.url, conDB.user, conDB.password);
            String q = ("insert into user(username, firstName, lastName, password) values (?,?,?,?)");
            PreparedStatement pst = conDB.connection.prepareStatement(q);

            pst.setString(1, mUI.usernameTF.getText());
            pst.setString(2, mUI.firstNameTF.getText());
            pst.setString(3, mUI.lastNameTF.getText());
            pst.setString(4, mUI.passwordTF.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "You have successfully registered!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void orderRanking() {
        try {
            String insideOrder = mUI.totalRatingBox.getSelectedItem().toString();
            if(insideOrder.equals("Ascending")){
                insideOrder = "ASC";
            } else {
                insideOrder = "DESC";
            }
            conDB.connection = DriverManager.getConnection(conDB.url, conDB.user, conDB.password);
            String q = ("select movieTitle as 'Movie Title', description as 'Description', rating as 'Rating' from movie order by rating " + insideOrder);
            PreparedStatement pst = conDB.connection.prepareStatement(q);
            conDB.result = pst.executeQuery();
            mUI.movieList.setModel(DbUtils.resultSetToTableModel(conDB.result));
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void goToUserReview() {
        try{
            conDB.connection = DriverManager.getConnection(conDB.url, conDB.user, conDB.password);
            String q = "select movie.id from movie where movieTitle = '" + mUI.valueOfTable + "'";
            PreparedStatement pst = conDB.connection.prepareStatement(q);
            conDB.result = pst.executeQuery(q);
            if(conDB.result.next()){
                String movieRating = "select username as 'Username', review as 'Review', rate as 'Rating' from rating where movieID = " + conDB.result.getInt("id");
                mUI.movieID = conDB.result.getInt("id");
                conDB.statement = conDB.connection.prepareStatement(movieRating);
                conDB.result = conDB.statement.executeQuery(movieRating);
                mUI.ratingList.setModel(DbUtils.resultSetToTableModel(conDB.result));
                vm.visibilityAtUserReview();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void goToInsideUserReview(){
        try{
            conDB.connection = DriverManager.getConnection(conDB.url, conDB.user, conDB.password);
            String q = null, q2 = null;
            if(mUI.valueInsideTheReview.equals(mUI.userCurrentlyUsing)) {
                mUI.alreadyReview = true;
                q = "select review from rating where username ='" + mUI.userCurrentlyUsing + "' and movieID =" + mUI.movieID;
                q2 = "select rate from rating where username = '" + mUI.userCurrentlyUsing + "' and movieID =" + mUI.movieID;
            } else {
                q = "select review from rating where username ='" + mUI.valueInsideTheReview + "' and movieID =" + mUI.movieID;
                q2 = "select rate from rating where username = '" + mUI.valueInsideTheReview + "' and movieID =" + mUI.movieID;
            }
            PreparedStatement pst = conDB.connection.prepareStatement(q);
            PreparedStatement pst2 = conDB.connection.prepareStatement(q2);
            conDB.result = pst.executeQuery(q);
            conDB.result.next();
            mUI.userNameIRLabel.setText(mUI.valueInsideTheReview +  "'s " + mUI.valueOfTable +  " Review");
            String review = conDB.result.getString("review");
            conDB.result = pst2.executeQuery(q2);
            conDB.result.next();
            Integer rate = conDB.result.getInt("rate");
            mUI.reviewTextArea.setText(review);
            mUI.toReviewCB.getModel().setSelectedItem(rate);
            vm.visibilityAtInsideUserReview();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    public void saveButtonFunction() {
        try{
            if(!mUI.valueInsideTheReview.equals(mUI.userCurrentlyUsing)){
                JOptionPane.showMessageDialog(null, "You can't edit someone else review!");
            } else {
                conDB.connection = DriverManager.getConnection(conDB.url, conDB.user, conDB.password);
                String q = " ";
                if(mUI.alreadyReview){
                    q = "update rating set review = '" + mUI.reviewTextArea.getText() + "', rate = " + mUI.toReviewCB.getSelectedItem()
                            + " where username = '" + mUI.userCurrentlyUsing + "' and movieID = " + mUI.movieID;

                } else {
                    q = "insert into rating values('" + mUI.userCurrentlyUsing + "', "+ mUI.toReviewCB.getSelectedItem() +", '" +
                            mUI.reviewTextArea.getText() + "', "+ mUI.movieID + ")";
                }
                PreparedStatement pst = conDB.connection.prepareStatement(q);
                pst.executeUpdate();
                mUI.changeRating();
                JOptionPane.showMessageDialog(null, "Your review have been saved :D");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void reviewButtonFunction() {
        try {
            conDB.connection = DriverManager.getConnection(conDB.url, conDB.user, conDB.password);
            String q = "select review from rating where username ='" + mUI.userCurrentlyUsing + "' and movieID =" + mUI.movieID;
            PreparedStatement pst = conDB.connection.prepareStatement(q);
            conDB.result = pst.executeQuery();
            mUI.userNameIRLabel.setText(mUI.userCurrentlyUsing +  "'s " + mUI.valueOfTable +  " Review");
            if(conDB.result.next()){
                mUI.alreadyReview = true;
                goToInsideUserReview();
            } else {
                mUI.alreadyReview = false;
                cleanSlateInsideReview();
                vm.visibilityAtInsideUserReview();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void cleanSlateInsideReview() {
        mUI.reviewTextArea.setText("");
        mUI.toReviewCB.getModel().setSelectedItem("");
    }

}
