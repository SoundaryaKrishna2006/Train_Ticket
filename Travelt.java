import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
class TrainRoute 
{
  String source, destination, amount;
  TrainRoute(String s, String d, String a) 
  {
    source = s.trim().toUpperCase();
    destination = d.trim().toUpperCase();
    amount = a;
  }
}
public class Travelt extends JFrame implements ActionListener 
{
  JTextField srcField, destField;
  JButton findButton, clearButton;
  ArrayList<TrainRoute> routes;
  Font labelFont = new Font("Segoe UI", Font.PLAIN, 17);
  JPanel mainPanel;

  public Travelt() 
  {
    ticketbooking();
  }
void showTicketDetails(String name,int age,String gender,String source,String destination,String fare)
{
  JFrame ticketFrame = new JFrame("Train Travel Ticket Details");
  ticketFrame.setSize(600, 400);
  ticketFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  JTextArea ticketArea = new JTextArea();
  ticketArea.setEditable(false);
  ticketArea.setFont(new Font("Consolas", Font.BOLD, 24));
  ticketArea.setText("\t\t*** PASSENGER DETAILS ***\n\n"+"Passenger's Name:"+name+"\n"+"Age:"+age+"\n"+"Gender:"+gender+"\n\n"+"\t\t*** TRAIN TRAVEL TICKET DETAILS ***\n\n"+ "Source Station      : " + source + "\n"+ "Destination Station : " + destination + "\n"+ "Fare Amount         : " + fare + "\n\n"+ "\t\t** Have a Safe Journey! **");
  ticketFrame.add(ticketArea);
  ticketFrame.setVisible(true);
  /*JFrame detailsFrame=new JFrame();
  detailsFrame.setSize(600, 600);
  detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  JPanel panel = new JPanel();
  panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
  panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

  JLabel nameLabel = new JLabel("Name: " + name);
  JLabel ageLabel = new JLabel("Age: " + age);
  JLabel genderLabel = new JLabel("Gender: " + gender);

  panel.add(nameLabel);
  panel.add(Box.createRigidArea(new Dimension(0, 10)));
  panel.add(ageLabel);
  panel.add(Box.createRigidArea(new Dimension(0, 10)));
  panel.add(genderLabel);

  detailsFrame.add(panel);
  detailsFrame.setVisible(true);*/
}
public void ticketbooking() 
{
  setTitle("Travel Ticket Booking Application");
  setSize(500, 400);
  setExtendedState(JFrame.MAXIMIZED_BOTH);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setLayout(new BorderLayout(10, 10));
   
  JLabel title = new JLabel("Travel Ticket Booking");
  title.setHorizontalAlignment(JLabel.CENTER);
  title.setFont(new Font("Segoe UI", Font.BOLD, 22));
  add(title, BorderLayout.NORTH);
        

  mainPanel = new JPanel();
  mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
  mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 10));
  JPanel formPanel = new JPanel(new GridBagLayout());
  formPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
  GridBagConstraints gbc = new GridBagConstraints();
  gbc.insets = new Insets(20, 20, 20, 20);
  gbc.fill = GridBagConstraints.HORIZONTAL;
  gbc.gridx = 0; gbc.gridy = 0;
  formPanel.add(new JLabel("Source Station:"), gbc);
  gbc.gridx = 1;
  srcField = new JTextField(40);
  srcField.setFont(new Font("Arial", Font.PLAIN, 24));
  formPanel.add(srcField, gbc);

  gbc.gridx = 0; gbc.gridy = 1;
  formPanel.add(new JLabel("Destination Station:"), gbc);
  gbc.gridx = 1;
  destField = new JTextField(40);
  destField.setFont(new Font("Arial", Font.PLAIN, 24));
  formPanel.add(destField, gbc);

  gbc.gridx = 0; gbc.gridy = 2;
  findButton = new JButton("Find Fare");
  findButton.setFont(new Font("Arial", Font.BOLD, 24));
  findButton.setPreferredSize(new Dimension(250, 60));
  formPanel.add(findButton, gbc);

  gbc.gridx = 1; gbc.gridy = 2;
  clearButton = new JButton("Clear");
  clearButton.setFont(new Font("Arial", Font.BOLD, 24));
  clearButton.setPreferredSize(new Dimension(250, 60));
  formPanel.add(clearButton, gbc);

  add(formPanel, BorderLayout.CENTER);

  findButton.addActionListener(this);
  clearButton.addActionListener(this);

  routes = new ArrayList<>();
  routes.add(new TrainRoute("Chennai", "Madurai", "₹500"));
  routes.add(new TrainRoute("Chennai", "Coimbatore", "₹480"));
  routes.add(new TrainRoute("Chennai", "Trichy", "₹350"));
  routes.add(new TrainRoute("Chennai", "Salem", "₹400"));
  routes.add(new TrainRoute("Madurai", "Trichy", "₹180"));
  routes.add(new TrainRoute("Madurai", "Tirunelveli", "₹150"));
  routes.add(new TrainRoute("Coimbatore", "Salem", "₹200"));
  routes.add(new TrainRoute("Coimbatore", "Trichy", "₹300"));
  routes.add(new TrainRoute("Trichy", "Salem", "₹220"));
  routes.add(new TrainRoute("Salem", "Tirunelveli", "₹450"));

  setVisible(true);
}
  public void actionPerformed(ActionEvent e)  
  {
    if (e.getSource() == clearButton) 
    {
        srcField.setText("");
        destField.setText("");
        return;
     }

     if (e.getSource() == findButton) 
     {
       String src = srcField.getText().trim();
       String dest = destField.getText().trim();
       if (src.isEmpty() || dest.isEmpty()) 
       {
                JOptionPane.showMessageDialog(this, "Please enter both Source and Destination!","Warning", JOptionPane.WARNING_MESSAGE);
                return;
       }
       boolean found = false;
       String fare = "";
       for (TrainRoute r : routes) 
       {
         if ((r.source.equalsIgnoreCase(src) && r.destination.equalsIgnoreCase(dest)) ||(r.source.equalsIgnoreCase(dest) && r.destination.equalsIgnoreCase(src))) 
         {
           fare = r.amount;
           found = true;
           break;
         }
       }
        if (found)
       {
         PassengerDetails(src, dest, fare);
       } 
       if(!found)
       {
         JOptionPane.showMessageDialog(this,"No fare data found between " + src + " and " + dest + ".","Error", JOptionPane.ERROR_MESSAGE);
       }
    }
}

public void PassengerDetails(String source,String dest,String fare)
{
  JFrame newFrame=new JFrame();
  newFrame.setTitle("Travel Ticket Booking Application");
  newFrame.setSize(500, 400);
  newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  newFrame.setLayout(new BorderLayout());

  JLabel title = new JLabel("Passenger's Details Registration");
  title.setHorizontalAlignment(JLabel.CENTER);
  title.setFont(new Font("Segoe UI", Font.BOLD, 22));
  newFrame.add(title, BorderLayout.NORTH);


   JPanel mainPanel = new JPanel(new GridBagLayout());
   GridBagConstraints gbc = new GridBagConstraints();
   gbc.insets = new Insets(30, 20, 30, 20);
   gbc.fill = GridBagConstraints.NONE;
   gbc.anchor = GridBagConstraints.CENTER;
   gbc.gridx = 0;
   gbc.gridy = 0;
  
  Font bigFont = new Font("Segoe UI", Font.BOLD, 28);   // Labels
  Font fieldFont = new Font("Segoe UI", Font.PLAIN, 26);

  JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
  JLabel nameLabel = new JLabel("Passenger's Name:");
  nameLabel.setFont(bigFont);
  JTextField nameField = new JTextField();
  nameField.setPreferredSize(new Dimension(400,40));
  nameField.setFont(fieldFont);
  namePanel.add(nameLabel);
  namePanel.add(nameField);
  mainPanel.add(namePanel, gbc); 
 
  gbc.gridy++;
  JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
  JLabel ageLabel = new JLabel("Passenger's Age:");
  ageLabel.setFont(bigFont);
  JTextField ageField = new JTextField();
  ageField.setPreferredSize(new Dimension(200, 40));
  ageField.setFont(fieldFont);
  agePanel.add(ageLabel);
  agePanel.add(ageField);
  mainPanel.add(agePanel, gbc); 
  
  gbc.gridy++;
  JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
  JLabel genderLabel = new JLabel("Gender:");
  genderLabel.setFont(labelFont);
  JRadioButton male = new JRadioButton("Male");
  JRadioButton female = new JRadioButton("Female");
  male.setFont(bigFont);
  female.setFont(bigFont);
  male.setPreferredSize(new Dimension(120, 40));
  female.setPreferredSize(new Dimension(140, 40));
  ButtonGroup genderGroup = new ButtonGroup();
  genderGroup.add(male);
  genderGroup.add(female);
  genderPanel.add(genderLabel);
  genderPanel.add(male);
  genderPanel.add(female);
  namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
  genderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
  agePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
  agePanel.setMaximumSize(new Dimension(450, ageField.getPreferredSize().height));
  namePanel.setMaximumSize(namePanel.getPreferredSize());
  genderPanel.setMaximumSize(genderPanel.getPreferredSize());
  mainPanel.add(genderPanel, gbc); 

  gbc.gridy++;
  JButton submitBtn = new JButton("Submit");
  submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
  submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 28));
  submitBtn.setPreferredSize(new Dimension(250, 70));
  mainPanel.add(submitBtn, gbc); 
 
  newFrame.add(mainPanel, BorderLayout.CENTER);
  newFrame.setVisible(true);

  submitBtn.addActionListener(e -> {
    String name = nameField.getText().trim();
    if(name.isEmpty()) 
    {
       JOptionPane.showMessageDialog(null, "Name cannot be empty");
       return;
    } 
    else if(!name.matches("[a-zA-Z ]+"))
    {
      JOptionPane.showMessageDialog(null, "Name can contain only letters and spaces");
      return;
     }
     String ageText = ageField.getText().trim();
     if (ageText.isEmpty()) 
     {
       JOptionPane.showMessageDialog(null, "Age cannot be empty");
       return;
     }
     int age;
     try 
     {
       age = Integer.parseInt(ageText);
       if (age <0) 
       {
          JOptionPane.showMessageDialog(null, "Age can't be negative");
          return;
       }
      } 
      catch (NumberFormatException ex)
      {
        JOptionPane.showMessageDialog(null, "Age must be a number");
         return;
      }
      if (!male.isSelected() && !female.isSelected()) 
      {
         JOptionPane.showMessageDialog(null, "Please select gender");
          return;
      }
      String gender=male.isSelected() ? "Male" : "Female";
      showTicketDetails(name,age,gender,source,dest,fare);
      newFrame.dispose();
    });
  }

  public static void main(String[] args) 
  {
     new Travelt();
  }
}