class MedianFinder {

    List<Integer> list;

    public MedianFinder() {
        list = new ArrayList<>();
    }
    
    public void addNum(int num) {
        list.add(num);
        Collections.sort(list);
    }
    
    public double findMedian() {
        double median = 0;
        int st = 0, en = list.size() - 1;
        int mid = st + (en - st) / 2;
        if(list.size() % 2 == 0){
            median = (double) (list.get(mid) + list.get(mid + 1)) / 2;
        }else{
            median = (double) list.get(mid);
        }
        return median;
    }
}
