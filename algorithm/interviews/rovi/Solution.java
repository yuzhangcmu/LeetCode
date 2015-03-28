package Algorithms.algorithm.interviews.rovi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    /*
     * Complete the function below.
     */
    
    static int cnt = 0;
    
    public static int f(int x) {
        cnt++;
        
        if (x < 1) {
            return 1;
        } else {
            return f(x - 1) + g(x);
        }
        
    }
    
    public static int g(int x) {
        if (x < 2) return 1;
        else 
            return f(x - 1) + g (x / 2);
    }

    public static void main(String[] strs) {
//        System.out.println(f(13));
//        System.out.println(cnt);
        try {
            String[] ret = findSlot();
            for (int i = 0; i < ret.length; i++) {
                System.out.println(ret[i]);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static String[] schedule() {
        // Charset charset = Charset.forName("US-ASCII");
        // try (BufferedReader reader = Files.newBufferedReader("OUTPUT_PATH",
        // charset)) {
        // String line = null;
        // while ((line = reader.readLine()) != null) {
        // System.out.println(line);
        // }
        // } catch (IOException x) {
        // System.err.format("IOException: %s%n", x);
        // }
        try {
            return findSlot();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static class Meeting {
        int id;

        int start;
        int end;
        int priority;

        public Meeting(int start, int end, int priority) {
            super();
            this.start = start;
            this.end = end;
            this.priority = priority;
        }
    }

    public static class Interval implements Comparable<Interval> {
        int start;
        int end;

        public Interval(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval o) {
            // TODO Auto-generated method stub
            return this.start - o.start;
        }
    }

    public static class Room {
        ArrayList<Interval> meetings;

        public Room() {
            super();
            this.meetings = new ArrayList<Interval>();
        }
    }

    static String[] findSlot() throws IOException {
        int rooms = 0;
        int meetings = 0;

        FileReader fr = new FileReader("test_cases_fk65oqn/dead2.txt");
        BufferedReader br = new BufferedReader(fr);
        String s;

        ArrayList<Meeting> meetingList = new ArrayList<Meeting>();

        int index = 0;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
            String[] strs = s.split(",");
            
            for (int i = 0; i < strs.length; i++) {
                strs[i] = strs[i].trim();
            }
            
            if (index == 0) {
                rooms = Integer.parseInt(strs[0]);
            } else if (index == 1) {
                meetings = Integer.parseInt(strs[0]);
            } else {
                int id = Integer.parseInt(strs[0]);
                int start = Integer.parseInt(strs[1]);
                int end = Integer.parseInt(strs[2]);
                int priority = Integer.parseInt(strs[3]);

                Meeting meet = new Meeting(start, end, priority);
                meet.id = id;

                meetingList.add(meet);

                // Only accept M meetings.
                if (meetingList.size() == meetings) {
                    break;
                }
            }
            index++;
        }

        fr.close();

        ArrayList<Room> roomsList = new ArrayList<Room>();
        for (int i = 0; i < rooms; i++) {
            roomsList.add(new Room());
        }

        Collections.sort(meetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                // TODO Auto-generated method stub
                int ret = o2.priority - o1.priority;
                if (ret != 0) {
                    return ret;
                }

                return o1.start - o2.start;
            }

        });

        for (int i = 0; i < meetingList.size(); i++) {
            Meeting meeting = meetingList.get(i);
            System.out.println(meeting.id + " " + meeting.start + " " + meeting.end
                    + " " + meeting.priority);
        }

        return arrangeMeetings(roomsList, meetingList, meetings);
    }

    public static String[] arrangeMeetings(ArrayList<Room> roomsList, 
            ArrayList<Meeting> meetList, int meetNum) {
        String[] tmp = new String[meetNum];
        int meetArranged = 0;
        
        // Arrange the meetings in the rooms.
        for (int i = 0; i < meetList.size(); i++) {
            for (int j = 0; j < roomsList.size(); j++) {
                // Try to put the meet into a room.
                Meeting meeting = meetList.get(i);
                if (insertMeeting(meeting, roomsList.get(j))) {
                    tmp[meetArranged++] = "" + meeting.id;
                    break;
                }
            }

            // If all the meeting has been arranged, break.
            if (meetArranged == meetNum) {
                break;
            }
        }
        
        String[] ret = new String[meetArranged];
        System.arraycopy(tmp, 0, ret, 0, meetArranged);
        
        return ret;
    }
    
    public static boolean insertMeeting(Meeting meeting, Room room) {
        Interval interval = new Interval(meeting.start, meeting.end);
        
        //System.out.println("The meeting: begin:" + meet.start + "end:" + meet.end + "meet id:" + meet.id);

        int size = room.meetings.size();
        int index = Collections.binarySearch(room.meetings, interval);
        if (index < 0) {
            index = -index - 1;
        }
        
        // Compare the new interval to the current interval.
        if (index < size && room.meetings.get(index).start < meeting.end) {
            return false;
        }
        
        // Compare the new interval to the last interval.
        if (index > 0 && room.meetings.get(index - 1).end > meeting.start) {
            return false;
        }

        room.meetings.add(index, interval);
        return true;
    }
    
    public static boolean isInteger1(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
