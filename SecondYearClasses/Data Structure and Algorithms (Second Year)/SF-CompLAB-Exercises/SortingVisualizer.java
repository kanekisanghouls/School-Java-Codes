// Sorting Visualizer GUI
import java.util.ArrayList;
import java.util.Collections;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SortingVisualizer {

    private static Thread sortingThread;

    public static VisualizerFrame frame;
    public static Integer[] toBeSorted;
    public static boolean isSorting = false;
    public static int sortDataCount = 100;
    public static int sleep = 20;
    public static int blockWidth;
    // Stepped depicts whether the values are incremental or random. True is incremental.
    public static boolean stepped = false;

    public static void main(String[] args) {
        frame = new VisualizerFrame();
        resetArray();
        frame.setLocationRelativeTo(null);
    }

    public static void resetArray(){
        // If we are currently in a sorting method, then isSorting should be true
        // We do not want to reinitialize/reset the array mid sort.
        if (isSorting) return;
        toBeSorted = new Integer[sortDataCount];
        blockWidth = (int) Math.max(Math.floor(500/sortDataCount), 1);
        for(int i = 0; i<toBeSorted.length; i++){
            if (stepped) {
                toBeSorted[i] = i;
            } else {
                toBeSorted[i] = (int) (sortDataCount*Math.random());
            }
        }
        // If we're using incremental values, they are already sorted. This shuffles it.
        if (stepped) {
            ArrayList<Integer> shuffleThis = new ArrayList<>();
            for (int i = 0; i < toBeSorted.length; i++) {
                shuffleThis.add(toBeSorted[i]);
            }
            Collections.shuffle(shuffleThis);
            toBeSorted = shuffleThis.toArray(toBeSorted);
        }
        frame.preDrawArray(toBeSorted);
    }

    public static void startSort(String type){

        if (sortingThread == null || !isSorting){

            resetArray();

            isSorting = true;

            switch(type){
                case "Bubble":
                    sortingThread = new Thread(new BubbleSort(toBeSorted, frame, false));
                    break;

                case "Selection":
                    sortingThread = new Thread(new SelectionSort(toBeSorted, frame, false));
                    break;

                case "Insertion":
                    sortingThread = new Thread(new InsertionSort(toBeSorted, frame, false));
                    break;

                case "Gnome":
                    sortingThread = new Thread(new GnomeSort(toBeSorted, frame, false));
                    break;

                case "Merge":
                    sortingThread = new Thread(new MergeSort());
                    break;

                case "Radix LSD":
                    sortingThread = new Thread(new RadixSort(toBeSorted, frame, true));
                    break;

                case "Radix MSD":
                    sortingThread = new Thread(new RadixSort(toBeSorted, frame, false));
                    break;

                case "Shell":
                    sortingThread = new Thread(new ShellSort());
                    break;

                case "Quandrix":
                    sortingThread = new Thread(new QuandrixSort());
                    break;

                case "Bubble(fast)":
                    sortingThread = new Thread(new BubbleSort(toBeSorted, frame, true));
                    break;

                case "Selection(fast)":
                    sortingThread = new Thread(new SelectionSort(toBeSorted, frame, true));
                    break;

                case "Insertion(fast)":
                    sortingThread = new Thread(new InsertionSort(toBeSorted, frame, true));
                    break;

                case "Gnome(fast)":
                    sortingThread = new Thread(new GnomeSort(toBeSorted, frame, true));
                    break;

                default:
                    isSorting = false;
                    return;
            }

            sortingThread.start();

        }

    }

}


 

@SuppressWarnings("serial")
class VisualizerFrame extends JFrame {

    private final int MAX_SPEED = 1000;
    private final int MIN_SPEED = 1;
    private final int MAX_SIZE = 500;
    private final int MIN_SIZE = 1;
    private final int DEFAULT_SPEED = 20;
    private final int DEFAULT_SIZE = 100;

    private final String[] Sorts = {"Bubble", "Selection", "Insertion", "Gnome", "Merge", "Radix LSD", "Radix MSD", "Shell", "Quandrix", "Bubble(fast)", "Selection(fast)", "Insertion(fast)", "Gnome(fast)"};

    private int sizeModifier;

    private JPanel wrapper;
    private JPanel arrayWrapper;
    private JPanel buttonWrapper;
    private JPanel[] squarePanels;
    private JButton start;
    private JComboBox<String> selection;
    private JSlider speed;
    private JSlider size;
    private JLabel speedVal;
    private JLabel sizeVal;
    private GridBagConstraints c;
    private JCheckBox stepped;

    public VisualizerFrame(){
        super("Sorting Visualizer");

        start = new JButton("Start");
        buttonWrapper = new JPanel();
        arrayWrapper = new JPanel();
        wrapper = new JPanel();
        selection = new JComboBox<String>();
        speed = new JSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
        size = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
        speedVal = new JLabel("Speed: 20 ms");
        sizeVal = new JLabel("Size: 100 values");
        stepped = new JCheckBox("Stepped Values");
        c = new GridBagConstraints();

        for(String s : Sorts) selection.addItem(s);

        arrayWrapper.setLayout(new GridBagLayout());
        wrapper.setLayout(new BorderLayout());

        c.insets = new Insets(0,1,0,1);
        c.anchor = GridBagConstraints.SOUTH;

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SortingVisualizer.startSort((String) selection.getSelectedItem());
            }
        });

        stepped.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SortingVisualizer.stepped = stepped.isSelected();
            }
        });

        speed.setMinorTickSpacing(10);
        speed.setMajorTickSpacing(100);
        speed.setPaintTicks(true);

        speed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                speedVal.setText(("Speed: " + Integer.toString(speed.getValue()) + "ms"));
                validate();
                SortingVisualizer.sleep = speed.getValue();
            }
        });

        size.setMinorTickSpacing(10);
        size.setMajorTickSpacing(100);
        size.setPaintTicks(true);

        size.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                sizeVal.setText(("Size: " + Integer.toString(size.getValue()) + " values"));
                validate();
                SortingVisualizer.sortDataCount = size.getValue();
            }
        });

        buttonWrapper.add(stepped);
        buttonWrapper.add(speedVal);
        buttonWrapper.add(speed);
        buttonWrapper.add(sizeVal);
        buttonWrapper.add(size);
        buttonWrapper.add(start);
        buttonWrapper.add(selection);

        wrapper.add(buttonWrapper, BorderLayout.SOUTH);
        wrapper.add(arrayWrapper);

        add(wrapper);

        setExtendedState(JFrame.MAXIMIZED_BOTH );

        addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                // Reset the sizeModifier
                // 90% of the windows height, divided by the size of the sorted array.
                sizeModifier = (int) ((getHeight()*0.9)/(squarePanels.length));
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                // Do nothing
            }

            @Override
            public void componentShown(ComponentEvent e) {
                // Do nothing
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                // Do nothing
            }

        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // preDrawArray reinitializes the array of panels that represent the values. They are set based on the size of the window.
    public void preDrawArray(Integer[] squares){
        squarePanels = new JPanel[SortingVisualizer.sortDataCount];
        arrayWrapper.removeAll();
        // 90% of the windows height, divided by the size of the sorted array.
        sizeModifier =  (int) ((getHeight()*0.9)/(squarePanels.length));
        for(int i = 0; i<SortingVisualizer.sortDataCount; i++){
            squarePanels[i] = new JPanel();
            squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]*sizeModifier));
            squarePanels[i].setBackground(Color.blue);
            arrayWrapper.add(squarePanels[i], c);
        }
        repaint();
        validate();
    }

    public void reDrawArray(Integer[] x){
        reDrawArray(x, -1);
    }

    public void reDrawArray(Integer[] x, int y){
        reDrawArray(x, y, -1);
    }

    public void reDrawArray(Integer[] x, int y, int z){
        reDrawArray(x, y, z, -1);
    }

    // reDrawArray does similar to preDrawArray except it does not reinitialize the panel array.
    public void reDrawArray(Integer[] squares, int working, int comparing, int reading){
        arrayWrapper.removeAll();
        for(int i = 0; i<squarePanels.length; i++){
            squarePanels[i] = new JPanel();
            squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]*sizeModifier));
            if (i == working){
                squarePanels[i].setBackground(Color.green);
            }else if(i == comparing){
                squarePanels[i].setBackground(Color.red);
            }else if(i == reading){
                squarePanels[i].setBackground(Color.yellow);
            }else{
                squarePanels[i].setBackground(Color.blue);
            }
            arrayWrapper.add(squarePanels[i], c);
        }
        repaint();
        validate();
    }

}




class ShellSort implements Runnable{
    
    public void run() {
        int temp = 0;
        Integer[] toBeSorted = SortingVisualizer.toBeSorted;
        int j = 0;
        
        for(int gap = toBeSorted.length/2; gap > 0; gap/=2){
            for(int i = gap; i<toBeSorted.length; i++){
                temp = toBeSorted[i];
                for (j = i; j>=gap && temp<toBeSorted[j-gap]; j -= gap){
                    toBeSorted[j] = toBeSorted[j-gap];
                    SortingVisualizer.frame.reDrawArray(toBeSorted, i, j);
                    try {
                        Thread.sleep(SortingVisualizer.sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                toBeSorted[j] = temp;
            }
        }
        
        SortingVisualizer.isSorting=false;
    }
}


 

class SelectionSort implements Runnable{
    
    private Integer[] toBeSorted;
    private VisualizerFrame frame;
    private boolean fast;
    
    public SelectionSort(Integer[] toBeSorted, VisualizerFrame frame, boolean fast) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
        this.fast = fast;
    }
    
    public void run() {
        if (fast) {
            sortFast();
        } else {
            sortSlow();
        }
        SortingVisualizer.isSorting=false;
    }
    
    public void sortFast(){
        int temp = 0;
        int selected = 0;
        for(int i = 0; i<toBeSorted.length; i++){
            selected = i;
            for(int j = toBeSorted.length-1; j>i; j--){
                if (toBeSorted[j] <= toBeSorted[selected]){
                    selected = j;
                }                
            }
            frame.reDrawArray(toBeSorted);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temp = toBeSorted[i];
            toBeSorted[i] = toBeSorted[selected];
            toBeSorted[selected]= temp;
        }
    }
    
    public void sortSlow() {
        int temp = 0;
        int selected = 0;
        for(int i = 0; i<toBeSorted.length; i++){
            selected = i;
            for(int j = toBeSorted.length-1; j>i; j--){
                
                if (toBeSorted[j] <= toBeSorted[selected]){
                    selected = j;
                }
                frame.reDrawArray(toBeSorted, selected, j-1);
                try {
                    Thread.sleep(SortingVisualizer.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }                
            }
            temp = toBeSorted[i];
            toBeSorted[i] = toBeSorted[selected];
            toBeSorted[selected]= temp;
        }
        frame.reDrawArray(toBeSorted);
    }

}


 
class RadixSort implements Runnable{
    
    private Integer[] toBeSorted;
    private VisualizerFrame frame;
    private boolean lsd;
    
    public RadixSort(Integer[] toBeSorted, VisualizerFrame frame, boolean lsd) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
        this.lsd = lsd;
    }
    
    public void run() {
        if (lsd) radixlsd(toBeSorted, 1);
        else radixmsd(toBeSorted, findDigit(toBeSorted));
        SortingVisualizer.isSorting=false;
    }
    
    private void radixlsd(Integer[] x, int digit){
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for(int i = 0; i<10; i++){
            buckets[i] = new ArrayList<Integer>();
        }
        int theDig = 0;
        int maxI = 0;
        for(int i = 0; i<x.length; i++){
            theDig = (int) (x[i]%Math.pow(10, digit));
            for(int t = 0; t<digit-1; t++){
                theDig/=10;
            }
            if (x[i] > maxI) maxI = x[i];
            frame.reDrawArray(x, -1, -1, i);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buckets[theDig].add(x[i]);
        }
        ArrayList<Integer> finalList = new ArrayList<>();
        for(int i = 0; i<10; i++){
            finalList.addAll(buckets[i]);
        }
        
        Integer[] y = finalList.toArray(new Integer[0]);
        
        frame.reDrawArray(y);
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (maxI < Math.pow(10, digit)) return;

        radixlsd(y, digit+1);
        
    }
    
    private void radixmsd(Integer[] x, int digit){
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for(int i = 0; i<10; i++){
            buckets[i] = new ArrayList<Integer>();
        }
        int theDig = 0;
        for(int i = 0; i<x.length; i++){
            theDig = (int) (x[i]%Math.pow(10, digit));
            for(int t = 0; t<digit-1; t++){
                theDig/=10;
            }
            frame.reDrawArray(x, -1, -1, i);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buckets[theDig].add(x[i]);
        }
        ArrayList<Integer> finalList = new ArrayList<>();
        for(int i = 0; i<10; i++){
            finalList.addAll(buckets[i]);
        }
        
        Integer[] y = finalList.toArray(new Integer[0]);
        
        frame.reDrawArray(y);
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (digit == 1) return;
        
        int beginning = 0;
        
        for (int i = 0; i < 10; i++) {
            y = radixmsd(y, digit-1, beginning, beginning + buckets[i].size());
            beginning += buckets[i].size();
        }
    }
    
    private Integer[]  radixmsd(Integer[] x, int digit, int begin, int end){
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for(int i = 0; i<10; i++){
            buckets[i] = new ArrayList<Integer>();
        }
        int theDig = 0;
        for(int i = begin; i<end; i++){
            theDig = (int) (x[i]%Math.pow(10, digit));
            for(int t = 0; t<digit-1; t++){
                theDig/=10;
            }
            frame.reDrawArray(x, -1, -1, i);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buckets[theDig].add(x[i]);
        }
        ArrayList<Integer> finalList = new ArrayList<>();
        
        for (int i = 0; i < begin; i++) {
            finalList.add(x[i]);
        }
        
        for(int i = 0; i<10; i++){
            finalList.addAll(buckets[i]);
        }
        
        for (int i = end; i < x.length; i++) {
            finalList.add(x[i]);
        }
        
        Integer[] y = finalList.toArray(new Integer[0]);
        
        frame.reDrawArray(y);
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (digit == 1) return y;
        
        int beginning = begin;
        
        for (int i = 0; i < 10; i++) {
            y = radixmsd(y, digit-1, beginning, beginning + buckets[i].size());
            beginning += buckets[i].size();
        }
        
        return y;
    }
    
    private int findDigit(Integer[] x) {
        int max = Integer.MIN_VALUE;
        int digit = 1;
        for (int i : x) {
            if (i > max) max = i;
        }
        while (max > 10) {
            max = max/10;
            digit++;
        }
        return digit;
    }

}


 
class QuandrixSort implements Runnable{

    public void run() {
        int temp = 0;
        Integer[] toBeSorted = SortingVisualizer.toBeSorted;
        int i = 0;

        if (toBeSorted.length % 3 == 0) {
            SortingVisualizer.isSorting=false;
            return;
        }

        boolean swapped = false;
        int doubleCheck = 0;
        boolean end = false;
        while(doubleCheck < 3){
            if (end) {
                swapped = false;
                end = false;
            }

            int j = i + 1;
            int k = j + 1;

            if (k < toBeSorted.length) {
                if (toBeSorted[k] < toBeSorted[j]) {
                    temp = toBeSorted[j];
                    toBeSorted[j] = toBeSorted[k];
                    toBeSorted[k] = temp;
                    swapped = true;
                }
                SortingVisualizer.frame.reDrawArray(toBeSorted, k, j);
                try {
                    Thread.sleep(SortingVisualizer.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (j < toBeSorted.length) {
                if (toBeSorted[j] < toBeSorted[i]) {
                    temp = toBeSorted[i];
                    toBeSorted[i] = toBeSorted[j];
                    toBeSorted[j] = temp;
                    swapped = true;
                }
                SortingVisualizer.frame.reDrawArray(toBeSorted, j, i);
                try {
                    Thread.sleep(SortingVisualizer.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (k < toBeSorted.length) {
                    if (toBeSorted[k] < toBeSorted[j]) {
                        temp = toBeSorted[j];
                        toBeSorted[j] = toBeSorted[k];
                        toBeSorted[k] = temp;
                        swapped = true;
                    }
                    SortingVisualizer.frame.reDrawArray(toBeSorted, k, j);
                    try {
                        Thread.sleep(SortingVisualizer.sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            i += 3;
            if (i >= toBeSorted.length) {
                i = i % toBeSorted.length;
                end = true;
                if (!swapped) {
                    doubleCheck++;
                } else {
                    doubleCheck = 0;
                }
            }
        }

        SortingVisualizer.isSorting=false;
    }
}


 


class MergeSort implements Runnable{
    
    public void run() {
        Integer[] toBeSorted = SortingVisualizer.toBeSorted;
        inPlaceSort(toBeSorted);
        SortingVisualizer.isSorting=false;
    }
    public void inPlaceSort ( Integer[] x )
       {  inPlaceSort (x, 0, x.length-1);  }

   private void inPlaceSort ( Integer[] x, int first, int last )
   {
      int mid, lt, rt;
      int tmp;

      if ( first >= last ) return;

      mid = (first + last) / 2;

      inPlaceSort (x, first, mid);
      inPlaceSort (x, mid+1, last);

      lt = first;  rt = mid+1;
      // One extra check:  can we SKIP the merge?
      if ( x[mid] <= x[rt])
         return;

      while (lt <= mid && rt <= last)
      {
         // Select from left:  no change, just advance lt
         if ( x[lt] <= x[rt])
            lt++;
         // Select from right:  rotate [lt..rt] and correct
         else
         {
            tmp = x[rt];     // Will move to [lt]
            for (int i = rt-lt;i>0; i--){
                x[lt+i] = x[lt+i-1];
            }
            x[lt] = tmp;
            // EVERYTHING has moved up by one
            lt++;  mid++;  rt++;
         }
         SortingVisualizer.frame.reDrawArray(x, mid, rt, lt);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
      }
      // Whatever remains in [rt..last] is in place
   }
}

 


class InsertionSort implements Runnable{
    
    private Integer[] toBeSorted;
    private VisualizerFrame frame;
    private boolean fast;
    
    public InsertionSort(Integer[] toBeSorted, VisualizerFrame frame, boolean fast) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
        this.fast = fast;
    }
    
    public void run() {
        if (fast) {
            sortFast();
        } else {
            sortSlow();
        }
        SortingVisualizer.isSorting=false;
    }
    
    public void sortFast() {
        int temp = 0;
        int insert = 0;
        for(int i = 1; i<toBeSorted.length; i++){
            insert = i;
            for(int j = i-1; j>=0; j--){
                if (toBeSorted[i] < toBeSorted[j]){
                    insert = j;
                    if (j == 0){
                        break;
                    }
                }else{
                    break;
                }
            }
            temp = toBeSorted[i];
            for (int j = i; j>insert; j--){
                toBeSorted[j] = toBeSorted[j-1];
            }
            toBeSorted[insert] = temp;
            frame.reDrawArray(toBeSorted, i);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void sortSlow() {
        int temp = 0;
        int insert = 0;
        for(int i = 1; i<toBeSorted.length; i++){
            insert = i;
            for(int j = i-1; j>=0; j--){
                if (toBeSorted[i] < toBeSorted[j]){
                    insert = j;
                    if (j == 0){
                        break;
                    }
                }else{
                    break;
                }
                frame.reDrawArray(toBeSorted, i, insert);
                try {
                    Thread.sleep(SortingVisualizer.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            temp = toBeSorted[i];
            for (int j = i; j>insert; j--){
                toBeSorted[j] = toBeSorted[j-1];
            }
            toBeSorted[insert] = temp;
        }
        frame.reDrawArray(toBeSorted);
    }
}




class GnomeSort implements Runnable{
    
    private Integer[] toBeSorted;
    private VisualizerFrame frame;
    private boolean fast;
    
    public GnomeSort(Integer[] toBeSorted, VisualizerFrame frame, boolean fast) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
        this.fast = fast;
    }
    
    public void run() {
        if (fast) {
            sortFast();
        } else {
            sortSlow();
        }
        SortingVisualizer.isSorting=false;
    }
    
    public void sortFast() {
        int temp = 0;
        for(int i = 0; i<toBeSorted.length-1; i++){
            for(int j = i+1; j>0; j--){
                if (toBeSorted[j] < toBeSorted[j-1]){
                    temp = toBeSorted[j];
                    toBeSorted[j] = toBeSorted[j-1];
                    toBeSorted[j-1] = temp;
                }else{
                    break;
                }
            }
            frame.reDrawArray(toBeSorted);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void sortSlow() {
        int temp = 0;
        for(int i = 0; i<toBeSorted.length-1; i++){
            for(int j = i+1; j>0; j--){
                if (toBeSorted[j] < toBeSorted[j-1]){
                    temp = toBeSorted[j];
                    toBeSorted[j] = toBeSorted[j-1];
                    toBeSorted[j-1] = temp;
                    frame.reDrawArray(toBeSorted, j, j-1);
                    try {
                        Thread.sleep(SortingVisualizer.sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}

 

class BubbleSort implements Runnable{
    
    private Integer[] toBeSorted;
    private VisualizerFrame frame;
    private boolean fast;
    
    public BubbleSort(Integer[] toBeSorted, VisualizerFrame frame, boolean fast) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
        this.fast = fast;
    }
    
    public void run() {
        if (fast) {
            sortFast();
        } else {
            sortSlow();
        }
        SortingVisualizer.isSorting=false;
    }
    
    public void sortFast() {
        int temp = 0;
        boolean swapped = false;
        for(int i = 0; i<toBeSorted.length-1; i++){
            swapped = false;
            for(int j = 1; j<toBeSorted.length-i; j++){
                if (toBeSorted[j-1]> toBeSorted[j]){
                    temp = toBeSorted[j-1];
                    toBeSorted[j-1] = toBeSorted[j];
                    toBeSorted[j]= temp;
                    swapped = true;
                }
            }
            frame.reDrawArray(toBeSorted);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!swapped) break;
        }
    }
    
    public void sortSlow() {
        int temp = 0;
        boolean swapped = false;
        for(int i = 0; i<toBeSorted.length-1; i++){
            swapped = false;
            for(int j = 1; j<toBeSorted.length-i; j++){
                if (toBeSorted[j-1]> toBeSorted[j]){
                    temp = toBeSorted[j-1];
                    toBeSorted[j-1] = toBeSorted[j];
                    toBeSorted[j]= temp;
                    swapped = true;
                }
                frame.reDrawArray(toBeSorted, j, j+1);
                try {
                    Thread.sleep(SortingVisualizer.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!swapped) break;
        }
    }

}