//package Algorithms.algorithm.interviews.pocketGem;
//
//import java.awt.Color;
//import java.util.List;
//
//public class Bejeweled {
//    class GridPoint {
//        int x;
//        int y;
//    }
//
//    class Item {
//        Color color;
//    }
//
//    abstract class Match {
//        GridPoint[] matchPoints;
//        abstract void removeMatchPoints();
//        abstract void addMatchPoint(GridPoint point);
//    }
//
//    class Game {
//        Item[][] board;
//        int boardWidth;
//        int boardHeight;
//        int currentScore;
//
//        // this is the callback for user interaction
//        void swap(GridPoint pointA, GridPoint pointB) {
//            // points A and B will be adjacent
//            Item originalItemAtPointA = board[pointA.x][pointA.y];
//            board[pointA.x][pointA.y] = board[pointB.x][pointB.y];
//            board[pointB.x][pointB.y] = originalItemAtPointA;
//            List<Match> matches = getMatches();
//            if (matches.length == 0) {
//                board[pointB.x][pointB.y] = board[pointA.x][pointA.y];
//                board[pointA.x][pointA.y] = originalItemAtPointA;
//            }
//            for (Match match in matches) {
//                for (GridPoint matchPoint in match.matchPoints) {
//                    board[matchPoint.x][matchPoint.y] = null;
//                    currentScore++;
//                }
//            }
//
//            if (currentScore >= 3) {
//                win(currentScore);
//            }
//        }
//
//        List<Match> getMatches() {
//            List<Match> foundMatches = new List<Match>();
//            foundMatches.add(getHorizontalMatches());
//            foundMatches.add(getVerticalMatches());
//            return foundMatches;
//        }
//
//        List<Match> getHorizontalMatches() {
//            List<Match> foundMatches = new List<Match>();
//            Match match = new Match();
//            for (int y = 0; y < boardHeight; y++) {
//                int x = 0;
//                GridPoint startingGridPoint = null;
//                Item startingItem = null;
//                match.removeMatchPoints();
//                while (x < boardWidth) {
//                    if (x < boardWidth && startingGridPoint == null) {
//                        startingGridPoint = new GridPoint(x, y);
//                        startingItem = board[x][y];
//                        if (startingItem) {
//                            match.addMatchPoint(startingGridPoint);
//                        }
//                        x++;
//                    } else {
//                        GridPoint nextGridPoint = new Pair(x, y);
//                        Item nextItem = board[x][y];
//                        if (startingItem && nextItem && nextItem.color.isEqual(startingItem.color)) {
//                            match.addMatchPoint(nextGridPoint);
//                            x++;
//
//                            if (x >= boardWidth) {
//                                if (match.matchPoints.length >= 3) {
//                                    foundMatches.append(match);
//                                    // 在这里调用
//                                    //randomItem(match);
//                                }
//                            }
//                        } else {
//                            if (match.matchPoints.length >= 3) {
//                                foundMatches.append(match);
//                                // 这里也调用
//                                // 在这里调用
//                                //randomItem(match);
//                            }
//
//                            match = new Match();
//                            startingGridPoint = null;
//                            startingItem = null;
//                        }
//                    }
//                }
//            }
//            return foundMatches;
//        }
//
//        List<Match> getVerticalMatches() {
//            //...
//            // add some thing here.
//            // put
//        }
//
//        Item randomItem() {
//
//        }
//    }
//
//}
//
