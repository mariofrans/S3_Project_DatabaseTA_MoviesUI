package DBTA;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class mainUI {
    // Panel
    public JPanel mainPanel;
    public JPanel loginMenu;
    public JPanel movieMenu;
    public JPanel registerPanel;
    public JPanel ratingMenu;
    public JPanel insideRatingMenu;

    // TextField
    public JTextField userNameTextField;
    public JTextField passwordTextField;
    public JTextField firstNameTF;
    public JTextField lastNameTF;
    public JTextField usernameTF;
    public JTextField passwordTF;

    // Button
    private JButton loginButton;
    private JButton registerButton;
    private JButton backButton1;
    private JButton signUpButton;
    private JButton logoutButton;
    private JButton backButton;
    private JButton backButtonIRM;
    private JButton saveButton;

    // Table
    public JTable movieList;
    public JTable ratingList;


    // ComboBox
    public JComboBox totalRatingBox;
    public JComboBox toReviewCB;

    // Label
    private JLabel movieListLabel;
    private JLabel ratingLabel;
    public JLabel movieNameLabel;
    public JLabel userNameIRLabel;

    // TextArea
    public JTextArea reviewTextArea;
    private JButton reviewButton;

    // Test Area
    public int mLRC;
    public String valueOfTable;
    public String valueInsideTheReview;
    public String userCurrentlyUsing;
    public Integer movieID;
    public boolean alreadyReview;

    visibilityManager vm = new visibilityManager(this);
    functions f = new functions(this, vm);

    public mainUI() {
        // Start
        createTotalRatingBox();
        createToReviewCB();
        changeRating();
        f.orderRanking();

        // Stuff
        movieList.getTableHeader().setBackground(Color.white);
        ratingList.getTableHeader().setBackground(Color.white);

        DefaultTableModel noEDIT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        // Login Menu
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.visibilityAtRegister();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userCurrentlyUsing = userNameTextField.getText();
                f.loginCheck();
                System.out.println(userCurrentlyUsing);
            }
        });

        // Register Menu
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.toRegister();
            }
        });

        backButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.visibilityAtStart();
            }
        });

        // Movie Menu
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.visibilityAtStart();
            }
        });

        totalRatingBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.orderRanking();
            }
        });

        // User Review Menu
        movieList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTable movieList = (JTable)e.getSource();
                int row = movieList.getSelectedRow();
                // JOptionPane.showMessageDialog(null, valueInCell);
                valueOfTable = (String)movieList.getValueAt(row, 0);
                movieNameLabel.setText(valueOfTable + " User Review");
                f.goToUserReview();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeRating();
                f.orderRanking();
                alreadyReview = false;
                vm.visibilityOnMovie();
            }
        });

        ratingList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTable ratingList = (JTable)e.getSource();
                int row = ratingList.getSelectedRow();
                valueInsideTheReview = (String)ratingList.getValueAt(row, 0);
                f.goToInsideUserReview();
            }
        });

        // Inside User Review

        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valueInsideTheReview = userCurrentlyUsing;
                f.reviewButtonFunction();
            }
        });

        backButtonIRM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.goToUserReview();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.saveButtonFunction();
            }
        });
    }

    public void createTotalRatingBox(){
        totalRatingBox.setModel(new DefaultComboBoxModel(new String[] {"Ascending", "Descending"}));
    }

    public void createToReviewCB() {
        toReviewCB.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void fetch() {
        try {
            conDB.connection = DriverManager.getConnection(conDB.url, conDB.user, conDB.password);
            String q = "select movieTitle as 'Movie Title', description as 'Description', rating as 'Rating' from movie";
            conDB.statement = conDB.connection.prepareStatement(q);
            conDB.result = conDB.statement.executeQuery(q);
            movieList.setModel(DbUtils.resultSetToTableModel(conDB.result));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void changeRating() {
        try {
            fetch();
            for(int i= 0; i <= movieList.getRowCount(); i++){
                conDB.connection = DriverManager.getConnection(conDB.url, conDB.user, conDB.password);
                String q = "update movie set rating = (select avg(rate) from rating where movieID = 10"+ i +") where id = 10"+ i;
                PreparedStatement pst = conDB.connection.prepareStatement(q);
                pst.executeUpdate();
            }
            fetch();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
