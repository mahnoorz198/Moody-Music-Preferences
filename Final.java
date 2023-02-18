import java.util.*;
public class Final {
    public static void main(String[] args) {
        /*instantiations*/
        BT resultsFromTests;  // BT class object
        LinkedList<String> playlist;
        String[] moods;
        String[] songRankings;
        Hashtable<String, String> songsAndRankings;
        String startingMood;

        try (Scanner keyboard = new Scanner(System.in)) {
            resultsFromTests = new BT();  // Here BT class constructor called

            int songChoice;
            playlist = new LinkedList<>();
            String[] artistNames = new String[4];

            moods = new String[4];
            songRankings = new String[5];

            Queue<String> q1 = new LinkedList<String>();
            Stack<String> playlistStack = new Stack<>();
            songsAndRankings = new Hashtable<String, String>();

            /* method calls */

            //puts the songs into the playlist Linked List
            createPlaylist(playlist);

            //puts moods into the moods array
            addEmotions(moods);

            //puts artist names into the artistNames array
            createArtistList(artistNames);

            //sorts the names in the artistNames array alphabetically
            sortNamesAlphabetically(artistNames);

            for (int i = 0; i < playlist.size(); i++) {
                playlistStack.push(playlist.get(i));
            }

            // It will print playlist in reversing mode
            Final.reversePrint(playlistStack);

            // Display songs list to user
            for (int i = 0; i < playlist.size(); i++) {
                System.out.println((i + 1) + " " + playlistStack.get(i));

            }

            System.out.println("\nWhich of these songs did you listen to all this morning?");

            songChoice = keyboard.nextInt();

            System.out.println("Song listened to this morning: " + playlist.get(songChoice - 1) + "\n");
            System.out.println("Mood: " + moods[songChoice - 1] + "\n");

            startingMood = moods[songChoice - 1];

            System.out.println("Now let's see how willing are you to listen to each of these songs right now."
                    + " Please rank each song that comes up on a scale of 1 - 5 \n");

            System.out.println("If you accidentally put in a score you don't like type \"undo\". If you know your"
                    + " score is correct make sure you enter an exclamation point on the line after your score\nto show"
                    + " that you're aggressively sure of your answer!\n");


            //adds song rankings to the songRankings array and checks to make sure the value is between 1 and 5
            for (int i = 0; i < playlist.size(); i++) {
                System.out.println(playlist.get(i));
                int scale = keyboard.nextInt();

                if (scale >=1 || scale <= 5) {
                    q1.add(String.valueOf(scale));
                    String answer = keyboard.next();

                    if (answer.equalsIgnoreCase("undo")) {
                        q1.remove(scale);
                        System.out.println("Enter the correct score:");
                        scale = keyboard.nextInt();
                        q1.add(String.valueOf(scale));
                    }

                    songRankings[i] = String.valueOf(scale);

                } else {
                    System.out.println("Please enter a number that is in the range of 1 - 5. Do not reuse number " +
                            "rankings");
                    i = i - 1;
                }
            }
        }

        //adding song rankings as keys and song titles as values to a hashtable
        for (int i = 0; i < playlist.size(); i++) {
            songsAndRankings.put(songRankings[i], playlist.get(i));
        }


        //checks what the highest key is and removes the songs ranked lower from the hashtable
        if (songsAndRankings.containsKey("5")) {
            songsAndRankings.remove("1");
            songsAndRankings.remove("2");
            songsAndRankings.remove("3");
            songsAndRankings.remove("4");

        } else if (songsAndRankings.containsKey("4")) {
            songsAndRankings.remove("1");
            songsAndRankings.remove("2");
            songsAndRankings.remove("3");

        } else if (songsAndRankings.containsKey("3")) {
            songsAndRankings.remove("1");
            songsAndRankings.remove("2");

        } else if (songsAndRankings.containsKey("2")) {
            songsAndRankings.remove("1");
        }

        //gets the remaining song name from the hashtable
        String finalSongChoice = String.valueOf(songsAndRankings.values());

        //removes the brackets from the string of the song name so that the equality test works
        finalSongChoice = finalSongChoice.substring(1, finalSongChoice.length() - 1);

        //searches through the original playlist and sets the corresponding moods index to indexOfMood
        for (int i = 0; i <= 3; i++) {
            if (playlist.get(i).equalsIgnoreCase(finalSongChoice)) {
                int indexOfMood = i;

                if (moods[indexOfMood].equalsIgnoreCase(startingMood)) {
                    System.out.println("The song you want to listen to most right now is " + finalSongChoice
                            + ". That songs mood is " + moods[indexOfMood].toLowerCase() + ". Earlier in the day your" +
                            " songs mood was " + startingMood.toLowerCase() + ". Therefore, your mood has not changed.");
                    System.out.println("You want to listen to the same song as you did this morning, the reason" +
                            " why is because your mood has not changed.");
                    resultsFromTests.insert(startingMood + " - " + finalSongChoice,1);

                } else {
                    System.out.println("The song you want to listen to most right now is " + finalSongChoice
                            + ". That songs mood is " + moods[indexOfMood].toLowerCase() + ". Earlier in the day your" +
                            " songs mood was " + startingMood.toLowerCase() + ". Therefore, your mood has changed.");
                    System.out.println("You don't to listen to the same song as you did this morning, the reason" +
                            " why is because your mood has changed.");
                    resultsFromTests.insert(startingMood + " - " + finalSongChoice,1);
                }

            }
        }

        resultsFromTests.insert(playlist.get(0), 2);
        resultsFromTests.insert(playlist.get(1),3);
        resultsFromTests.insert(playlist.get(2),4);
        System.out.println("Printing Tree");
        System.out.println(resultsFromTests.inorder());
        System.out.println(" ");

    }
    public static void createPlaylist(LinkedList playlist) {
        playlist.add("Zitti e Buoni");
        playlist.add("Mary On A Cross");
        playlist.add("Graceland Too");
        playlist.add("Cruel Summer");
    }

    public static void createArtistList(String[] artists) {
        artists[0] = ("Maneskin");
        artists[1] = ("Ghost");
        artists[2] = ("Phoebe Bridgers");
        artists[3] = ("Taylor Swift");
    }

    public static void sortNamesAlphabetically(String [] names) {
        String temp;
        for (int j = 0; j < names.length; j++) {
            for (int i = j + 1; i < names.length; i++) {

                // compares adjacent strings
                if (names[i].compareTo(names[j]) < 0) {
                    temp = names[j];
                    names[j] = names[i];
                    names[i] = temp;
                }
            }
        }
    }

    public static void addEmotions(String[] emotions) {
        emotions[0] = "Angry";
        emotions[1] = "Empowering";
        emotions[2] = "Sad";
        emotions[3] = "Happy";
    }

    public static void reversePrint(Stack<String> stack) {
        // creates temporary stack
        Stack<String> temporaryStack = new Stack<>();

        // copies all elements from given stack to temporary stack
        while(stack.size()>0) {
            temporaryStack.push(stack.pop());
        }

        // prints all elements from temporary stack, & restores all elements to given stack
        while(temporaryStack.size()>0){
            String x = temporaryStack.pop();

            stack.push(x);
        }
    }
}