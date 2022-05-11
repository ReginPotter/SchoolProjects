package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;

class Main extends JFrame {

    // all of these are used in lambda expressions
    JLabel background;
    JLabel battery = new JLabel();
    JButton left;
    JButton right;
    JButton forward;
    JButton back;
    JSlider slider;
    JPanel west;
    JPanel game;
    JPanel root;
    JPanel helpPage;
    int numBack;
    boolean gen1Start = false;
    boolean gen2Start = false;
    boolean gen3Start = false;
    boolean gen4Start = false;
    int count;

    public Main() throws IOException {

        // the first few blocks are to create everything needed for the main menu
        JPanel root = new JPanel() {

            // this gives the root JPanel a background image
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = null;
                try {
                    image = ImageIO.read(new File("resources/scp.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(image, -240, -230, null);
            }
        };
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel startMenu = new JPanel();
        startMenu.setLayout(new GridLayout());
        startMenu.setOpaque(false);

        JLabel gameName = new JLabel();
        gameName.setForeground(Color.white);
        gameName.setOpaque(false);
        gameName.setFont(new Font("Arial", Font.PLAIN, 30));
        gameName.setText("SCP - Containment Breach");
        gameName.setBackground(Color.black);
        gameName.setHorizontalAlignment(JLabel.CENTER);

        JPanel startButtons = new JPanel();
        startButtons.setBackground(Color.black);
        startButtons.setOpaque(false);
        startButtons.setBorder(new EmptyBorder(10, 10 ,10, 10));
        startButtons.setLayout(new FlowLayout());

        // this creates the start button
        JButton start = new JButton("Start");
        start.setBackground(Color.black);
        start.setForeground(Color.white);

        // when you press the start button a new game starts
        start.addActionListener(e -> {

            // the first few blocks are setting up the game
            Game newGame = new Game();
            DoublyLinkedList rooms = new DoublyLinkedList();
            Room startRoom = new Room("Elevator #4", 4, false, true, false);
            rooms.add(startRoom);

            game = new JPanel();
            game.setLayout(new BorderLayout());
            game.setOpaque(true);

            JPanel helpInfo = new JPanel();
            helpInfo.setLayout(new BoxLayout(helpInfo, BoxLayout.Y_AXIS));

            ImageIcon img = new ImageIcon("resources/help.jpg");
            JButton help = new JButton("");
            help.setIcon(img);
            help.setAlignmentX(LEFT_ALIGNMENT);
            help.setBackground(Color.black);
            help.setForeground(Color.white);

            background = new JLabel();
            background.setIcon(new ImageIcon("resources/4_entrances.jpg"));

            west = new JPanel();
            west.setLayout(new BorderLayout());

            JPanel arrowButtons = new JPanel();
            arrowButtons.setLayout(new GridLayout(2, 2));

            right = new JButton("->");

            left = new JButton("<-");

            forward = new JButton("^");

            back = new JButton("v");

            JButton startGen = new JButton("Start Gen");
            startGen.setVisible(false);

            slider = new JSlider(JSlider.VERTICAL, 0, 5, 0);
            slider.setMajorTickSpacing(1);
            slider.setSnapToTicks(true);
            slider.setPaintTicks(true);
            slider.setVisible(false);

            arrowButtons.add(left);
            arrowButtons.add(right);
            arrowButtons.add(forward);
            arrowButtons.add(back);

            west.add(arrowButtons, BorderLayout.SOUTH);
            west.add(battery, BorderLayout.CENTER);
            west.add(startGen, BorderLayout.NORTH);

            helpInfo.add(help);
            helpInfo.add(slider);

            game.add(west, BorderLayout.WEST);
            game.add(helpInfo, BorderLayout.EAST);
            game.add(background, BorderLayout.CENTER);

            this.getContentPane().add(game);

            root.setVisible(false);
            game.setVisible(true);

            // when you press the help button it goes into the setHelpPage method
            help.addActionListener(f -> {
                setHelpPage(game);
            });

            // when you press the left button this happens
            left.addActionListener(f -> {
                slider.setValue(0);
                startGen.setVisible(false);
                forward.setEnabled(true);
                left.setEnabled(true);
                right.setEnabled(true);

                numBack = 0;

                Room room = newGame.createRoom();
                room.setIsGen();
                rooms.add(room);

                // if the room is a generator it goes into here
                if(room.getIsGen()) {
                    System.out.println(count);

                    // this changes the image of the battery
                    switch(count) {
                        case 0: battery.setIcon(new ImageIcon("resources/load_0%.jpg"));
                            break;
                        case 1: battery.setIcon(new ImageIcon("resources/load_25%.jpg"));
                            break;
                        case 2: battery.setIcon(new ImageIcon("resources/load_50%.jpg"));
                            break;
                        case 3: battery.setIcon(new ImageIcon("resources/load_75%.jpg"));
                            break;
                        case 4: battery.setIcon(new ImageIcon("resources/load_100%.jpg"));
                            background.setIcon(new ImageIcon("resources/blink.jpg"));
                            break;
                    }

                    // if the SCP is in the room when the screen is black it kills you and ends the game
                    if (count == 4) {
                        if(room.getIsEnemyHere()) {
                            JOptionPane.showMessageDialog(this, "SCP 173 has killed you!", "Oh no!", JOptionPane.PLAIN_MESSAGE);
                            root.setVisible(true);
                            game.setVisible(false);
                        }
                        count = 0;
                    } else {
                        count++;
                    }

                    // if the new game doesn't work it sets the button to false
                    if (!newGame.roomWorks(room)) {
                        startGen.setEnabled(false);
                    }
                    startGen.setVisible(true);
                    left.setEnabled(false);
                    right.setEnabled(false);
                    background.setIcon(new ImageIcon("resources/generator_room.jpg"));

                    // when the startGen button is pressed this happens
                    startGen.addActionListener(h -> {
                        JButton run = new JButton("run");
                        run.setVisible(true);
                        startGen.setVisible(false);

                        west.add(run, BorderLayout.NORTH);

                        background.setIcon(new ImageIcon("resources/generator.jpg"));
                        slider.setVisible(true);

                        right.setEnabled(false);
                        left.setEnabled(false);
                        forward.setEnabled(false);
                        back.setEnabled(false);

                        // when the run button is pressed this happens
                        run.addActionListener(g -> {
                            if (slider.getValue() == 5) {
                                background.setIcon(new ImageIcon("resources/generator_room.jpg"));
                                right.setEnabled(false);
                                left.setEnabled(false);
                                forward.setEnabled(true);
                                back.setEnabled(true);
                                slider.setVisible(false);
                                run.setVisible(false);
                                if(!gen1Start) {
                                    gen1Start = true;
                                } else if(!gen2Start) {
                                    gen2Start = true;
                                } else if(!gen3Start) {
                                    gen3Start = true;
                                } if(!gen4Start) {
                                    gen4Start = true;
                                }
                            }
                        });

                    });
                }

                // if the room isnt a generator it creates the respective rooms
                else {
                    if (room.getExits() == 4) {
                        entrances_4(room);
                    } else if (room.getExits() == 3) {
                        entrances_3(room);
                    } else if (room.getExits() == 2) {
                        entrances_2(room);
                    } else {
                        entrance_1(room);
                    }
                }

                // this checks if the game is over
                if (room.getName().equalsIgnoreCase("Elevator #4")) {
                    if (gen1Start && gen2Start && gen3Start && gen4Start) {
                        JOptionPane.showMessageDialog(this, "You have won the game!", "Congratulations!", JOptionPane.PLAIN_MESSAGE);
                        root.setVisible(true);
                        game.setVisible(false);
                    }
                }

            });

            // when the right button is pressed these things happen
            right.addActionListener(f -> {
                slider.setValue(0);
                startGen.setVisible(false);
                forward.setEnabled(true);
                left.setEnabled(true);
                right.setEnabled(true);

                numBack = 0;

                Room room = newGame.createRoom();
                room.setIsGen();
                rooms.add(room);

                // this checks if the room is a generator
                if(room.getIsGen()) {
                    System.out.println(count);

                    // this changes the image of the battery
                    switch(count) {
                        case 0: battery.setIcon(new ImageIcon("resources/load_0%.jpg"));
                            break;
                        case 1: battery.setIcon(new ImageIcon("resources/load_25%.jpg"));
                            break;
                        case 2: battery.setIcon(new ImageIcon("resources/load_50%.jpg"));
                            break;
                        case 3: battery.setIcon(new ImageIcon("resources/load_75%.jpg"));
                            break;
                        case 4: battery.setIcon(new ImageIcon("resources/load_100%.jpg"));
                            background.setIcon(new ImageIcon("resources/blink.jpg"));
                            break;
                    }

                    // if the SCP is in the room when the screen is black it kills you and ends the game
                    if (count == 4) {
                        if(room.getIsEnemyHere()) {
                            JOptionPane.showMessageDialog(this, "SCP 173 has killed you!", "Oh no!", JOptionPane.PLAIN_MESSAGE);
                            root.setVisible(true);
                            game.setVisible(false);
                        }
                        count = 0;
                    } else {
                        count++;
                    }

                    // if the room doesn't work it makes the startGen button not work
                    if (!newGame.roomWorks(room)) {
                        startGen.setEnabled(false);
                    }
                    startGen.setVisible(true);
                    left.setEnabled(false);
                    right.setEnabled(false);
                    background.setIcon(new ImageIcon("resources/generator_room.jpg"));

                    // when the startGen button is pressed it goes into here
                    startGen.addActionListener(h -> {
                        JButton run = new JButton("run");
                        run.setVisible(true);
                        startGen.setVisible(false);

                        west.add(run, BorderLayout.NORTH);

                        background.setIcon(new ImageIcon("resources/generator.jpg"));
                        slider.setVisible(true);

                        right.setEnabled(false);
                        left.setEnabled(false);
                        forward.setEnabled(false);
                        back.setEnabled(false);

                        // when the run button is pressed it goes into here
                        run.addActionListener(g -> {
                            if (slider.getValue() == 5) {
                                background.setIcon(new ImageIcon("resources/generator_room.jpg"));
                                right.setEnabled(false);
                                left.setEnabled(false);
                                forward.setEnabled(true);
                                back.setEnabled(true);
                                slider.setVisible(false);
                                run.setVisible(false);

                                if(!gen1Start) {
                                    gen1Start = true;
                                } else if(!gen2Start) {
                                    gen2Start = true;
                                } else if(!gen3Start) {
                                    gen3Start = true;
                                } else {
                                    gen4Start = true;
                                }
                            }
                        });
                    });
                }

                // if the room isnt a generator it creates the respective rooms
                else {
                    if (room.getExits() == 4) {
                        entrances_4(room);
                    } else if (room.getExits() == 3) {
                        entrances_3(room);
                    } else if (room.getExits() == 2) {
                        entrances_2(room);
                    } else {
                        entrance_1(room);
                    }
                }

                // this checks if the game is over
                if (room.getName().equalsIgnoreCase("Elevator #4")) {
                    if (gen1Start && gen2Start && gen3Start && gen4Start) {
                        JOptionPane.showMessageDialog(this, "You have won the game!", "Congratulations!", JOptionPane.PLAIN_MESSAGE);
                        root.setVisible(true);
                        game.setVisible(false);
                    }
                }

            });

            // when the forward button is pressed this happens
            forward.addActionListener(f -> {
                slider.setValue(0);
                startGen.setVisible(false);
                forward.setEnabled(true);
                left.setEnabled(true);
                right.setEnabled(true);

                numBack = 0;

                Room room = newGame.createRoom();
                room.setIsGen();
                rooms.add(room);

                // if the room is a generator it does this
                if(room.getIsGen()) {
                    System.out.println(count);

                    // this updates the battery picture
                    switch(count) {
                        case 0: battery.setIcon(new ImageIcon("resources/load_0%.jpg"));
                            break;
                        case 1: battery.setIcon(new ImageIcon("resources/load_25%.jpg"));
                            break;
                        case 2: battery.setIcon(new ImageIcon("resources/load_50%.jpg"));
                            break;
                        case 3: battery.setIcon(new ImageIcon("resources/load_75%.jpg"));
                            break;
                        case 4: battery.setIcon(new ImageIcon("resources/load_100%.jpg"));
                            background.setIcon(new ImageIcon("resources/blink.jpg"));
                            break;
                    }

                    // if the enemy is here and the screen is black it kills the player and ends the game
                    if (count == 4) {
                        if(room.getIsEnemyHere()) {
                            JOptionPane.showMessageDialog(this, "SCP 173 has killed you!", "Oh no!", JOptionPane.PLAIN_MESSAGE);
                            root.setVisible(true);
                            game.setVisible(false);
                        }
                        count = 0;
                    } else {
                        count++;
                    }

                    // if the room doesn't work it sets the startGen button to false
                    if (!newGame.roomWorks(room)) {
                        startGen.setEnabled(false);
                    }
                    startGen.setVisible(true);
                    left.setEnabled(false);
                    right.setEnabled(false);
                    background.setIcon(new ImageIcon("resources/generator_room.jpg"));

                    // when the startGen button is pressed this happens
                    startGen.addActionListener(h -> {
                        JButton run = new JButton("run");
                        run.setVisible(true);
                        startGen.setVisible(false);

                        west.add(run, BorderLayout.NORTH);

                        background.setIcon(new ImageIcon("resources/generator.jpg"));
                        slider.setVisible(true);

                        right.setEnabled(false);
                        left.setEnabled(false);
                        forward.setEnabled(false);
                        back.setEnabled(false);

                        // when the run button is pressed this happens
                        run.addActionListener(g -> {
                            if (slider.getValue() == 5) {
                                background.setIcon(new ImageIcon("resources/generator_room.jpg"));
                                right.setEnabled(false);
                                left.setEnabled(false);
                                forward.setEnabled(true);
                                back.setEnabled(true);
                                slider.setVisible(false);
                                run.setVisible(false);
                                if(!gen1Start) {
                                    gen1Start = true;
                                } else if(!gen2Start) {
                                    gen2Start = true;
                                } else if(!gen3Start) {
                                    gen3Start = true;
                                } else {
                                    gen4Start = true;
                                }
                            }
                        });
                    });
                }

                // if the room isn't a generator it creates the respective room
                else {
                    if (room.getExits() == 4) {
                        entrances_4(room);
                    } else if (room.getExits() == 3) {
                        entrances_3(room);
                    } else if (room.getExits() == 2) {
                        entrances_2(room);
                    } else {
                        entrance_1(room);
                    }
                }

                // this checks if the game is over
                if (room.getName().equalsIgnoreCase("Elevator #4")) {
                    if (gen1Start && gen2Start && gen3Start && gen4Start) {
                        JOptionPane.showMessageDialog(this, "You have won the game!", "Congratulations!", JOptionPane.PLAIN_MESSAGE);
                        root.setVisible(true);
                        game.setVisible(false);
                    }
                }

            });

            numBack = 0;

            // when the back button is pressed this happens
            back.addActionListener(f -> {
                slider.setValue(0);
                startGen.setVisible(false);
                forward.setEnabled(true);
                left.setEnabled(true);
                right.setEnabled(true);

                // this deletes the last room from the doubly linked list
                rooms.deleteLast();

                // this sets the room as the last room in the doubly linked list
                Room room = rooms.getItemAt(rooms.size() - 1);
                numBack += 1;

                // if the room is a generator this happens
                if(room.getIsGen()) {
                    System.out.println(count);

                    // this updates the battery image
                    switch(count) {
                        case 0: battery.setIcon(new ImageIcon("resources/load_0%.jpg"));
                            break;
                        case 1: battery.setIcon(new ImageIcon("resources/load_25%.jpg"));
                            break;
                        case 2: battery.setIcon(new ImageIcon("resources/load_50%.jpg"));
                            break;
                        case 3: battery.setIcon(new ImageIcon("resources/load_75%.jpg"));
                            break;
                        case 4: battery.setIcon(new ImageIcon("resources/load_100%.jpg"));
                            background.setIcon(new ImageIcon("resources/blink.jpg"));
                            break;
                    }

                    // if the screen is black and the SCP is there it kills the player and ends the game
                    if (count == 4) {
                        if(room.getIsEnemyHere()) {
                            JOptionPane.showMessageDialog(this, "SCP 173 has killed you!", "Oh no!", JOptionPane.PLAIN_MESSAGE);
                            root.setVisible(true);
                            game.setVisible(false);
                        }
                        count = 0;
                    } else {
                        count++;
                    }

                    // if the room doesn't work it sets the startGen button to false
                    if (!newGame.roomWorks(room)) {
                        startGen.setEnabled(false);
                    }
                    startGen.setVisible(true);
                    left.setEnabled(false);
                    right.setEnabled(false);
                    background.setIcon(new ImageIcon("resources/generator_room.jpg"));

                    // if the startGen button is pressed this happens
                    startGen.addActionListener(h -> {
                        JButton run = new JButton("run");
                        run.setVisible(true);
                        startGen.setVisible(false);

                        west.add(run, BorderLayout.NORTH);

                        background.setIcon(new ImageIcon("resources/generator.jpg"));
                        slider.setVisible(true);

                        right.setEnabled(false);
                        left.setEnabled(false);
                        forward.setEnabled(false);
                        back.setEnabled(false);

                        // if the run button is pressed this happens
                        run.addActionListener(g -> {
                            if (slider.getValue() == 5) {
                                background.setIcon(new ImageIcon("resources/generator_room.jpg"));
                                right.setEnabled(false);
                                left.setEnabled(false);
                                forward.setEnabled(true);
                                back.setEnabled(true);
                                slider.setVisible(false);
                                run.setVisible(false);
                                if(!gen1Start) {
                                    gen1Start = true;
                                } else if(!gen2Start) {
                                    gen2Start = true;
                                } else if(!gen3Start) {
                                    gen3Start = true;
                                } else {
                                    gen4Start = true;
                                }
                            }
                        });
                    });
                }

                // if the room isn't a generator it creates the respective room
                else {
                    if (room.getExits() == 4) {
                        entrances_4(room);
                    } else if (room.getExits() == 3) {
                        entrances_3(room);
                    } else if (room.getExits() == 2) {
                        entrances_2(room);
                    } else {
                        entrance_1(room);
                    }
                }

                // this checks if the game is over
                if (room.getName().equalsIgnoreCase("Elevator #4")) {
                    if (gen1Start && gen2Start && gen3Start && gen4Start) {
                        JOptionPane.showMessageDialog(this, "You have won the game!", "Congratulations!", JOptionPane.PLAIN_MESSAGE);
                        root.setVisible(true);
                        game.setVisible(false);
                    }
                }

            });
        });

        // this creates the help button
        JButton help = new JButton("Help");
        help.setBackground(Color.black);
        help.setForeground(Color.white);

        // when you press the help button it goes into the setHelpPage method
        help.addActionListener(e -> {
            setHelpPage(root);
        });

        // this sets up the load button
        JButton load = new JButton("Load");
        load.setBackground(Color.black);
        load.setForeground(Color.white);

        // when you press the load button the load JPanel is visible
        load.addActionListener(e -> {

            // the beginning blocks are just setting up the loadPage JPanel
            JPanel loadPage = new JPanel();
            loadPage.setLayout(new BoxLayout(loadPage, BoxLayout.Y_AXIS));
            loadPage.setBackground(Color.black);

            JPanel loadButtons = new JPanel();
            loadButtons.setLayout(new GridLayout(3, 1));
            loadButtons.setBorder(new EmptyBorder(50, 50, 50, 50));
            loadButtons.setBackground(Color.black);

            JButton load1 = new JButton("Load 1");
            load1.setBackground(Color.darkGray);
            load1.setForeground(Color.white);
            JButton load2 = new JButton("Load 2");
            load2.setBackground(Color.darkGray);
            load2.setForeground(Color.white);
            JButton load3 = new JButton("Load 3");
            load3.setBackground(Color.darkGray);
            load3.setForeground(Color.white);

            JButton back = new JButton("Back");
            back.setAlignmentX(CENTER_ALIGNMENT);
            back.setBackground(Color.black);
            back.setForeground(Color.white);

            loadButtons.add(load1);
            loadButtons.add(load2);
            loadButtons.add(load3);

            loadPage.add(loadButtons);
            loadPage.add(back);
            this.getContentPane().add(loadPage);

            root.setVisible(false);
            loadPage.setVisible(true);

            // when you press the load 1 button this happens
            load1.addActionListener(f -> {
                JOptionPane.showMessageDialog(this, "The dev hasn't coded this yet!", "ERROR", JOptionPane.PLAIN_MESSAGE);
            });

            // when you press the load 2 button this happens
            load2.addActionListener(f -> {
                JOptionPane.showMessageDialog(this, "The dev hasn't coded this yet!", "ERROR", JOptionPane.PLAIN_MESSAGE);
            });

            // when you press the load 3 button this happens
            load3.addActionListener(f -> {
                JOptionPane.showMessageDialog(this, "The dev hasn't coded this yet!", "ERROR", JOptionPane.PLAIN_MESSAGE);
            });

            // when you press back it takes you back to the main menu
            back.addActionListener(f -> {
                loadPage.setVisible(false);
                root.setVisible(true);

                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            });
        });

        // this sets up the quit button
        JButton quit = new JButton("Quit");
        quit.setBackground(Color.black);
        quit.setForeground(Color.white);

        // this tells the player thank you for playing and lets them quit
        quit.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thank you for playing!", "Goodbye", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        });

        // this sets up the rest of the items needed in the game menu
        startMenu.add(gameName);

        startButtons.add(start);
        startButtons.add(load);
        startButtons.add(help);
        startButtons.add(quit);

        root.add(startMenu);

        root.add(startButtons);

        this.getContentPane().add(root);

        // this sets up the GUI
        this.setTitle("SCP - Containment Breach");
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    // this sets the pictures of the room with four entrances
    public void entrances_4(Room room){
        System.out.println(count);

        if(room.getIsEnemyHere()) {
            background.setIcon(new ImageIcon("resources/SCP_4.png"));
        } else {
            background.setIcon(new ImageIcon("resources/4_entrances.jpg"));
        }

        switch(count) {
            case 0: battery.setIcon(new ImageIcon("resources/load_0%.jpg"));
            break;
            case 1: battery.setIcon(new ImageIcon("resources/load_25%.jpg"));
            break;
            case 2: battery.setIcon(new ImageIcon("resources/load_50%.jpg"));
                break;
            case 3: battery.setIcon(new ImageIcon("resources/load_75%.jpg"));
                break;
            case 4: battery.setIcon(new ImageIcon("resources/load_100%.jpg"));
                background.setIcon(new ImageIcon("resources/blink.jpg"));
                break;
        }

        if (count == 4) {
            if(room.getIsEnemyHere()) {
                JOptionPane.showMessageDialog(this, "SCP 173 has killed you!", "Oh no!", JOptionPane.PLAIN_MESSAGE);
                root.setVisible(true);
                game.setVisible(false);
            }
            count = 0;
        } else {
            count++;
        }
    }

    // this sets the pictures of the room with three entrances
    public void entrances_3(Room room) {
        System.out.println(count);

        forward.setEnabled(false);
        if(room.getIsEnemyHere()) {
            background.setIcon(new ImageIcon("resources/SCP_3.png"));
        } else {
            background.setIcon(new ImageIcon("resources/3_entrances.png"));
        }

        switch(count) {
            case 0: battery.setIcon(new ImageIcon("resources/load_0%.jpg"));
                break;
            case 1: battery.setIcon(new ImageIcon("resources/load_25%.jpg"));
                break;
            case 2: battery.setIcon(new ImageIcon("resources/load_50%.jpg"));
                break;
            case 3: battery.setIcon(new ImageIcon("resources/load_75%.jpg"));
                break;
            case 4: battery.setIcon(new ImageIcon("resources/load_100%.jpg"));
                background.setIcon(new ImageIcon("resources/blink.jpg"));
                break;
        }

        if (count == 4) {
            if(room.getIsEnemyHere()) {
                JOptionPane.showMessageDialog(this, "SCP 173 has killed you!", "Oh no!", JOptionPane.PLAIN_MESSAGE);
                root.setVisible(true);
                game.setVisible(false);
            }
            count = 0;
        } else {
            count++;
        }
    }

    // this sets the pictures of the room with two entrances
    public void entrances_2(Room room) {
        System.out.println(count);

        left.setEnabled(false);
        right.setEnabled(false);
        if(room.getIsEnemyHere()) {
            background.setIcon(new ImageIcon("resources/SCP_2.png"));
        } else {
            background.setIcon(new ImageIcon("resources/2_entrances.jpg"));
        }

        switch(count) {
            case 0: battery.setIcon(new ImageIcon("resources/load_0%.jpg"));
                break;
            case 1: battery.setIcon(new ImageIcon("resources/load_25%.jpg"));
                break;
            case 2: battery.setIcon(new ImageIcon("resources/load_50%.jpg"));
                break;
            case 3: battery.setIcon(new ImageIcon("resources/load_75%.jpg"));
                break;
            case 4: battery.setIcon(new ImageIcon("resources/load_100%.jpg"));
                background.setIcon(new ImageIcon("resources/blink.jpg"));
                break;
        }

        if (count == 4) {
            if(room.getIsEnemyHere()) {
                JOptionPane.showMessageDialog(this, "SCP 173 has killed you!", "Oh no!", JOptionPane.PLAIN_MESSAGE);
                root.setVisible(true);
                game.setVisible(false);
            }
            count = 0;
        } else {
            count++;
        }
    }

    // this sets the pictures of the room with one entrance
    public void entrance_1(Room room) {
        System.out.println(count);

        forward.setEnabled(false);
        right.setEnabled(false);
        left.setEnabled(false);
        if(room.getIsEnemyHere()) {
            background.setIcon(new ImageIcon("resources/SCP_1.png"));
        } else {
            background.setIcon(new ImageIcon("resources/1_entrance.png"));
        }

        switch(count) {
            case 0: battery.setIcon(new ImageIcon("resources/load_0%.jpg"));
                break;
            case 1: battery.setIcon(new ImageIcon("resources/load_25%.jpg"));
                break;
            case 2: battery.setIcon(new ImageIcon("resources/load_50%.jpg"));
                break;
            case 3: battery.setIcon(new ImageIcon("resources/load_75%.jpg"));
                break;
            case 4: battery.setIcon(new ImageIcon("resources/load_100%.jpg"));
                background.setIcon(new ImageIcon("resources/blink.jpg"));
                break;
        }
        if (count == 4) {
            if(room.getIsEnemyHere()) {
                JOptionPane.showMessageDialog(this, "SCP 173 has killed you!", "Oh no!", JOptionPane.PLAIN_MESSAGE);
                root.setVisible(true);
                game.setVisible(false);
            }
            count = 0;
        } else {
            count++;
        }
    }

    // this sets the help page and shows it
    public void setHelpPage(JPanel previous) {
        // these first few blocks are to set up the variables needed
        helpPage = new JPanel();
        helpPage.setLayout(new BorderLayout());
        helpPage.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextArea helpInfo = new JTextArea();
        helpInfo.setText("You are an employee at the SCP Facility. They had a containment breach but everything was contained and you are to go back to your job.\nHowever, in order to return to your job you need to turn back on 4 generators. They are randomly located in the facility and you need to find them and turn them on.\nYou move around using the arrows on the bottom left of your screen. When you find a generator you must click the 'Start gen' button in the upper left corner.\nOnce you've done this you must drag the slider on the right side of the screen all the way up and press the 'run' button in the upper left.\nYou must turn on four generators and then go back to the beginning area to win.\nThere might be another SCP roaming the area, SCP 173, and we have heard the power went out. Luckily, you have a flashlight.\nHowever, the battery is very bad and it goes out sometimes.\nDO NOT LET THE POWER GO OUT WHEN YOU ARE WITH SCP 173. YOU WILL DIE\nYour power gauge is on the left side of your screen. When it hits 100% it will turn off for one room. These flashlights are really weird I know.\nIf you ever need help and a refresher on how to survive, click the '?' button in the upper left of your screen. Good luck!");
        helpInfo.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton back = new JButton("Back");

        helpInfo.setBounds(100, 100, 100, 100);

        helpPage.setBackground(Color.black);
        helpInfo.setBackground(Color.black);
        helpInfo.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setForeground(Color.white);

        helpPage.add(helpInfo, BorderLayout.CENTER);
        helpPage.add(back, BorderLayout.SOUTH);

        // this adds the helpPage to the content pane and sets it to visible while setting the previous one to not visible
        this.getContentPane().add(helpPage);

        helpPage.setVisible(true);
        previous.setVisible(false);

        // when back is pressed it goes back to the previous JPanel
        back.addActionListener(e -> {
            helpPage.setVisible(false);
            previous.setVisible(true);
        });
    }

    // this runs the GUI
    public static void main(String[] args) {

        // this creates a new Main method for the GUI to run in
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}