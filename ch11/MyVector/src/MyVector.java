import java.util.*;

public class MyVector implements List{
	Object[] data = null;
	int capacity = 0;
	int size = 0;
	
	public MyVector(int capacity){
		if(capacity < 0){
			throw new IllegalArgumentException("유효하지 않은 값입니다. : " + capacity);
		}
		this.capacity = capacity;
		data = new Object[capacity];
	}
	
	public MyVector(){
		this(10);
	}
	
	public void ensureCapacity(int minCapacity){
		if(minCapacity - data.length > 0)
			setCapacity(minCapacity);
	}
	
	public boolean add(Object obj){
		ensureCapacity(size+1);
		data[size++] = obj;
		return true;
	}
	
	public Object get(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("범위를 벗어났습니다.");
		}
		
		return data[index];
	}
	
	public Object remove(int index){
		Object oldObj = null;
		
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("범위를 벗어났습니다.");
		}
		oldObj = data[index];
		
		if(index != size-1){
			System.arraycopy(data, index+1, data, index, size-index-1);
		}
		data[size-1] = null;
		size--;
		
		return oldObj;
	}
	
	public boolean remove(Object obj){
		for(int i=0; i<size; i++){
			if(obj.equals(data[i])){
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	
}
