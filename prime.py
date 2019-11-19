#!/usr/bin/python3
import math

print(2)
for i in range(3, 100) :
    flag = 1
    #从2除到被判断数的平方根，若没有可以整除的便是质数
    for j in range(2, int(math.sqrt(i) + 0.5) + 1) :
        if i % j == 0 :
            flag = 0
            break;
    if flag == 1 :
        print(i)