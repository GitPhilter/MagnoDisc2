package sort;

import training.perception.Perception;

public final class Merge {


    public static Perception[] sortPerceptions(Perception[] elements, int left, int right){
        int length = right - left;
        //System.out.println("length: " + length + ", left:" + left + ", right:" + right);
        if(length <= 0){
            if(length == 0){
                Perception[] result = (Perception[]) new Perception[1];
                result[0] = elements[left];
                return result;
            } else {
                return null;
            }
        };
        int mid = (int)((length - 1) / 2) + left;
        // left
        Perception[] leftArray = sortPerceptions(elements, left, mid);
        // right
        Perception[] rightArray = sortPerceptions(elements, mid + 1, right);
        // put together
        Perception[] result = new Perception[right - left + 1];
        //System.out.println("putting together...");
        int leftPointer = 0;
        int rightPointer = 0;
        int resultPointer = 0;

        if(leftArray != null && rightArray != null){
            while(leftPointer < leftArray.length && rightPointer < rightArray.length){
                if(leftArray[leftPointer].compareTo(rightArray[rightPointer]) > 0){
                    result[resultPointer] = leftArray[leftPointer];
                    ++leftPointer;
                } else {
                    result[resultPointer] = rightArray[rightPointer];
                    ++rightPointer;
                }
                ++resultPointer;
            }
        }
        if(leftArray != null){
            while(leftPointer < leftArray.length){
                result[resultPointer] = leftArray[leftPointer];
                ++leftPointer;
                ++resultPointer;
            }
        }
        if(rightArray != null){
            while(rightPointer < rightArray.length){
                result[resultPointer] = rightArray[rightPointer];
                ++rightPointer;
                ++resultPointer;
            }
        }
        return result;
    }
}
