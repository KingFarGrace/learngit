#!/usr/bin/python3

PI = 3.1415926
print("请输入圆的半径：")
s = input()
r = int(s)
#求面积
area = float(r * r * PI)
#求周长
length = float(2 * r * PI)
print("半径为", r, "米 的圆周长为:", length, "米 面积为:", area, "平方米")
