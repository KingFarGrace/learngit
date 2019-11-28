#!/usr/bin/python3
import random


'''
生成一次数据，返回值为xy坐标的集合
返回集合中包含180个相关点和20个无关点，相关点噪声为10
一次函数形为kx+b，其中k=1,b=5
'''
def get_scatter_data_of_line() :
    x_axis = []
    y_axis = []
    noise = 10
    while len(x_axis) <= 180:
        x = random.uniform(0, 200)
        y = x + 5 + random.uniform(-noise, noise)
        if y < 500:
            x_axis.append(x)
            y_axis.append(y)
    return x_axis + [random.uniform(0, 200) for i in range(20)], \
        y_axis + [random.uniform(0, 500) for i in range(20)]


'''
生成二次数据，返回值为xy坐标的集合
返回集合中包含180个相关点和20个无关点，相关点噪声为10
二次函数形为ax^2+bx+c，其中a=1,b=2,c=-3
'''
def get_scatter_data_of_quad():
    x_axis = []
    y_axis = []
    noise = 10
    while len(x_axis) <= 180:
        x = random.uniform(0, 200)
        y = x ** 2 + 2 * x - 3 + random.uniform(-noise, noise)
        if y < 500:
            x_axis.append(x)
            y_axis.append(y)
    return x_axis + [random.uniform(0, 200) for i in range(20)], \
        y_axis + [random.uniform(0, 500) for i in range(20)]


'''
将数据集写入文件中
参数列表从左到右依次为：x坐标集，y坐标集，文件路径
'''
def write_data_in_file(data_set_x, data_set_y, file_path):
    File = open(file_path, mode='w')
    for i in range(200):
        File.write('x:' + str(data_set_x[i]) + '\t\t' + 'y:' + str(data_set_y[i]) + '\n')
    File.close()


quad_data_set_x, quad_data_set_y = get_scatter_data_of_quad()
print('二次数据集中有', len(quad_data_set_x), '个点', '\n')
line_data_set_x, line_data_set_y = get_scatter_data_of_line()
print('一次数据集中有', len(quad_data_set_x), '个点', '\n')
quad_file_path = r"D:\MyPython\quadratic_data.txt"
line_file_path = r"D:\MyPython\linear_data.txt"
write_data_in_file(quad_data_set_x, quad_data_set_y, quad_file_path)
write_data_in_file(line_data_set_x, line_data_set_y, line_file_path)
