import java.lang.*;
import java.util.*;

public class ParallelSort extends Thread{
        String [] data;
        int low;
        int high;
        int calldepth;
        int cutoff;

        ParallelSort(String [] d, int l, int h, int cd, int c){
                this.data = d;
                this.low = l;
                this.high = h;
                this.calldepth = cd;
                this.cutoff = c;
        }

        ParallelSort(){
        //Empty constructor
        }

        public void run() {
                if(calldepth < cutoff){
                        int i = low - 1;
                        /*variable scoping*/{
                                String temp;
                                int j;
                                int mid = (high + low) >> 1;
                                String pivotvalue = data[mid];
                                data[mid] = data[high];
                                data[high] = pivotvalue;
                                for(j = low; j < high; j++){
                                        if(data[j].compareToIgnoreCase(pivotvalue) <= 0){
                                                i++;
                                                temp = data[i];
                                                data[i] = data[j];
                                                data[j] = temp;
                                        }//if
                                }//for
                                temp = data[i + 1];
                                data[i + 1] = data[high];
                                data[high] = temp;
                        }//variableScooping
                        calldepth++;
                        ParallelSort thread1 = new ParallelSort(data, low, i, calldepth, cutoff);
                        if(low < i){
                                thread1.start();
                        }//if low < i
                        ParallelSort thread2 = new ParallelSort(data, i + 2, high, calldepth, cutoff);
                        if((i + 2) < high){
                                thread2.start();
                        }//if (i + 2) < high
                         if(low < i){
                                try{
                                        thread1.join();
                                } catch (InterruptedException e){ }//try catch
                        }//if low < i
                        if((i + 2) < high){
                                try{
                                        thread2.join();
                                } catch (InterruptedException e){ }//try catch
                        }//if (i + 2) < high

                        }else{
                                String temp;
                                int length = high - low + 1;
                                int lcv;
                                int location;
                                int left, right;
                                int largest;
                                for(lcv = (length >> 1) - 1; lcv >= 0; lcv--){
                                        location = lcv;
                                        do{
                                                left = (location << 1) + 1;
                                                right = left + 1;
                                                if((left + low <= high) && (data[left + low].compareToIgnoreCase(data[location + low]) > 0)){
                                                        largest = left;
                                                }else{
                                                        largest = location;
                                                }//if else
                                                if((right + low <= high) && (data[right + low].compareToIgnoreCase(data[largest + low]) > 0)){
                                                        largest = right;
                                                }//if
                                                if(largest != location){
                                                        temp = data[location + low];
                                                        data[location + low] = data[largest + low];
                                                        data[largest + low] = temp;
                                                        location = largest;
                                                        largest = -1;
                                                }//if
                                        }while(largest!=location);
                        }//if else
                        for(lcv = length - 1; lcv > 0; lcv--){
                                temp = data[lcv + low];
                                data[lcv + low]=data[low];
                                data[low]=temp;
                                high--;
                                location = 0;
                                do{
                                        left = (location << 1) + 1;
                                        right = left + 1;
                                        if((left + low<= high) && (data[left + low].compareToIgnoreCase(data[location + low]) > 0)){
                                                largest = left;

                                        }else{
                                                largest = location;
                                        }//if else
                                        if((right + low<= high) && (data[right + low].compareToIgnoreCase(data[largest + low]) > 0)){
                                                largest = right;
                                        }//if
                                        if(largest != location){
                                                temp = data[location + low];
                                                data[location + low] = data[largest + low];
                                                data[largest + low] = temp;
                                                location = largest;
                                                largest = -1;
                                        }//if
                                }while(largest != location);
                        }//for
                }//if
        }//run

        void QuickParallelSort(String[] data){
                if(data != null){
                ParallelSort t = new ParallelSort(data, 0, data.length - 1, 0,
                        (int)((double)7 * StrictMath.log10((double)data.length)));
                                 // 7*log base 10 is approximately 2*log base 2
                t.start();
                try{
                        t.join();
                } catch(InterruptedException e){}
                }//try catch
        }//QuickParallelSort

        public static void main (String [] args) {
                String [] test = {"apple", "soup" , "dog" , "food" , "grape" , "happy" ,"jack" , "king" , "lars" , "quote" ,"wood" , "expect" ,"right" ,
                 "tie" ,"yes" , "ubuntu","iTunes" , "office" ,"peep" , "zoo" , "xavier" , "cook" ,"verb" , "break" , "noose" , "milk", "guitar", "blink", "siren",
                "roman", "Weasel", "ginger", "who", "snap", "crackle", "pop", "center", "mistletoe", "chubby", "pie", "Edgar", "Allen", "Poe", "Tori", "converse",
                "coke", "punk", "shoe", "slave", "chair", "pink", "dog", "tree", "people", "caddy", "shack", "vehicle", "Saturn", "Ford", "Chevy", "BMW", "Buick",
                "hunter", "van", "gray", "aqua", "dot", "boyfriend", "girlfriend", "husband", "wife", "love", "kids", "travel", "boat", "coat", "Donde", "cold", "bob",
                "craze", "game", "throne", "elder", "scrolls", "oblivion", "oblivious", "zombies", "movie", "hotel", "hostel", "jump", "Holocaust", "turkey", "thanks",
                "ham", "pork", "bacon", "beef", "chicken", "dirt", "grass", "clarinet", "pole", "flag", "shirt", "concert", "crowd", "loud", "proud", "mosque", "fish"};
                ParallelSort p = new ParallelSort();
                p.QuickParallelSort(test);
                for (int i = 0; i < test.length; i++){
                        System.out.println(test[i]);
                }//for
        }//main
}// QuickHeapSortOnStrings
