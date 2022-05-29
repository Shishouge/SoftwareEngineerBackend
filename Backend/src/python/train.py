import re
from typing import Counter
from snownlp import SnowNLP
from snownlp import sentiment
import matplotlib.pyplot as plt
import sys
from wordcloud import WordCloud
import jieba
import os

def cleanData():
    f = open("E:\\大三上\\软件工程\\data\\Dataset\\train.txt",encoding='utf8')             # 返回一个文件对象  
    line = f.readline()                                                                   # 调用文件的 readline()方法  
    neg=open('E:\\大三上\\软件工程\\data\\trainData\\neg.txt','w',encoding='utf8')
    pos=open('E:\\大三上\\软件工程\\data\\trainData\\pos.txt','w',encoding='utf8')
    while line:         
        if(line[0]=='1'):
            newLine=line.strip("1").replace(' ','').replace('\t','')
            neg.write(newLine)
        else:
            newLine=line.strip("0").replace(' ','').replace('\t','')
            pos.write(newLine)
        line=f.readline()  
    f.close()  
    neg.close
    pos.close

def train():
    print('The program is training......')
    sentiment.train('E:\\大三上\\软件工程\\data\\trainData\\neg.txt','E:\\大三上\\软件工程\\data\\trainData\\pos.txt')
    sentiment.save('E:\\大三上\\软件工程\\data\\trainData\\new.marshal')
    print('done')

def chinese_jieba(text):
    wordlist_jieba=jieba.cut(text)
    space_wordlist=''.join(wordlist_jieba)
    return  space_wordlist

if __name__ == "__main__":
    id=sys.argv[1]
    #id='1'
    filename= os.path.dirname(__file__) +'/testdata/review.txt'
    f = open(filename,encoding='utf8')
    line = f.readline()  
    result=[]
    while(line):
        s=SnowNLP(line)
        result.append(s.sentiments)
        #print(s.words)
        line=f.readline()
    f.close
    output=[0,0,0,0,0,0,0,0,0,0]
    output_x=[0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1]
    for i in range(len(result)):
        if(result[i]>0 and result[i]<=0.1):
            output[0]+=1
        elif(result[i]>0.1 and result[i]<=0.2):
            output[1]+=1
        elif(result[i]>0.2 and result[i]<=0.3):
            output[2]+=1
        elif(result[i]>0.3 and result[i]<=0.4):
            output[3]+=1
        elif(result[i]>0.4 and result[i]<=0.5):
            output[4]+=1
        elif(result[i]>0.5 and result[i]<=0.6):
            output[5]+=1
        elif(result[i]>0.6 and result[i]<=0.7):
            output[6]+=1
        elif(result[i]>0.7 and result[i]<=0.8):
            output[7]+=1
        elif(result[i]>0.8 and result[i]<=0.9):
            output[8]+=1
        elif(result[i]>0.9 and result[i]<=1):
            output[9]+=1
    plt.rcParams['font.sans-serif']=['SimHei'] #显示中文标签
    plt.rcParams['axes.unicode_minus']=False   #这两行需要手动设置
    plt.xlabel('评分')
    plt.ylabel('数量')
    plt.title('情感分析统计图')
    y = [10, 11, 12, 13, 14, 15, 16, 17, 18, 19]  # 这个是y轴的数据
    first_bar = plt.bar(range(len(output)), output, color='blue')  # 初版柱形图，x轴0-9，y轴是列表y的数据，颜色是蓝色
    index = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    plt.xticks(index, output_x)  # 绘制x轴的标签
    # 柱形图顶端数值显示
    for data in first_bar:
        y = data.get_height()
        x = data.get_x()
        plt.text(x + 0.35, y, str(y), va='bottom')  # 0.15为偏移值，可以自己调整，正好在柱形图顶部正中
    # 图片的显示及存储
    filename= os.path.dirname(__file__) +"/testdata/"+str(id)+".png"
    plt.savefig(filename)
    #plt.show()

    filename= os.path.dirname(__file__) +"/testdata/review.txt"
    with open(filename,encoding='utf-8')as file:
        text=file.read()
        text=chinese_jieba(text)
        wordcloud = WordCloud(font_path="simfang.ttf",
                            background_color="white", width=600,
                            height=300, max_words=50,min_font_size=8).generate(text)
        image=wordcloud.to_image()
        #image.show()
        filename= os.path.dirname(__file__) +"/testdata/"+str(id)+"_cloud.png"
        image.save(filename)
    file.close