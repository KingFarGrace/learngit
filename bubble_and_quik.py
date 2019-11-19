#!/user/bin/python3

#冒泡排序（升序）
def bubble_sort(num_arr) :
    bound = len(num_arr)
    for i in range(bound) :
        for j in range(bound - i - 1) :
            if num_arr[j] > num_arr[j + 1] :
                t = num_arr[j]
                num_arr[j] = num_arr[j + 1]
                num_arr[j + 1] = t

#二分查找（非递归）
def binary_search(num_arr, index) :
    low = 0
    high = len(num_arr) - 1
    if num_arr[low] == index :
        return low
    elif num_arr[high] == index :
        return high
    else :
        while high != low :
            mid = int((low + high) / 2)
            if num_arr[mid] == index :
                return mid
            elif num_arr[mid] < index :
                low = mid + 1
            else :
                high = mid - 1
    return None

num_arr = []
num = int(input("请输入列表元素个数:"))
for i in range(num) :
    print("请输入第", i + 1, "个元素:")
    elem = int(input())
    num_arr.append(elem)

print("冒泡排序的结果为:")
bubble_sort(num_arr)
print(num_arr)

index = int(input("请输入需要查找的元素:"))
flag = binary_search(num_arr, index)
if flag != None :
    print(index, "在表中索引数为:", flag)
else :
    print("没有找到该元素!")