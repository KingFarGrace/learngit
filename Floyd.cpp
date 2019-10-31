#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXSIZE 20

typedef int PATHINT[MAXSIZE];

typedef struct Queue
{
	int front;
	int rear;
	int queueSize;
	char data[MAXSIZE];
};

typedef struct MGraph
{
	int vexNum;
	int arcNum;
	char vexs[MAXSIZE];
	int arcs[MAXSIZE][MAXSIZE];
};

void flushVisited(int visited[], int length);
void createMGraph(MGraph* G);
void printMGraph(MGraph G);
void dfTraver(MGraph G, int v, int visited[]);
void dfTraverUnaccess(MGraph G, int visited[]);
void bfTraver(MGraph G, int v, int visited[]);
void bfTraverUnaccess(MGraph G, int visited[]);
void floyd(MGraph G);

int initQueue(Queue* Q);
int queueEmpty(Queue Q);
int queueFull(Queue Q);
int getHead(Queue Q, int* v);
int enQueue(Queue* Q, int v);
int deQueue(Queue* Q, int* v);

/*
 *
 */

void main()
{
	MGraph mg;
	int visited[MAXSIZE] = { 0 };

	createMGraph(&mg);
	printf("\n邻接矩阵如下\n");
	printMGraph(mg);
	printf("\n深度遍历序列如下\n\n");
	dfTraverUnaccess(mg, visited);
	printf("\n广度遍历算法如下\n\n");
	bfTraverUnaccess(mg, visited);
	printf("\n弗洛伊德算法所得路径如下\n");
	floyd(mg);

	system("pause");
}

/////////////////////////////////////////////////

void flushVisited(int visited[], int length) 
{
	register int i;

	for (i = 0; i < length; i++)
		visited[i] = 0;
}

/////////////////////////////////////////////////

void createMGraph(MGraph* G)
{
	register int i, j;
	int v1, v2, w;
	
	printf("请输入结点数和边数:");
	scanf("%d%d", &G->vexNum, &G->arcNum);
	getchar();

	printf("请输入结点信息:");
	for (i = 0; i < G->vexNum; i++)
		scanf("%c", &G->vexs[i]);
	getchar();

	for(i = 0; i < G->vexNum; i++)
		for (j = 0; j < G->vexNum; j++)
		{
			if (i == j)
				G->arcs[i][j] = 0;
			else
				G->arcs[i][j] = 10000;
		}

	for (i = 0; i < G->arcNum; i++)
	{
		printf("请输入第%d条弧的始点和终点以及弧权值:", i + 1);
		scanf("%d%d%d", &v1, &v2, &w);
		getchar();
		
		G->arcs[v1 - 1][v2 - 1] = w;
	}
}

/////////////////////////////////////////////////

void printMGraph(MGraph G)
{
	register int i, j;

	printf("\n————————————————————\n");
	for (i = 0; i < G.vexNum; i++)
	{
		for (j = 0; j < G.vexNum; j++)
			printf("%8d|", G.arcs[i][j]);
		printf("\n————————————————————\n");
	}
}

/////////////////////////////////////////////////

void dfTraver(MGraph G, int v, int visited[])
{
	register int i;
	visited[v] = 1;
	printf("%c", G.vexs[v]);

	for (i = 0; i < G.vexNum; i++)
		if (G.arcs[v][i] && G.arcs[v][i] < 10000 && !visited[i])
			dfTraver(G, i, visited);
}

void dfTraverUnaccess(MGraph G, int visited[])
{
	register int i;
	flushVisited(visited, G.vexNum);

	for (i = 0; i < G.vexNum; i++)
		if (!visited[i])
			dfTraver(G, i, visited);

	printf("\n");
}

/////////////////////////////////////////////////

void bfTraver(MGraph G, int v, int visited[])
{
	register int i;
	int w;
	Queue q;
	initQueue(&q);
	printf("%c", G.vexs[v]);
	visited[v] = 1;
	enQueue(&q, v);
	while (!queueEmpty(q))
	{
		deQueue(&q, &w);
		for (i = 0; i < G.vexNum; i++)
			if (G.arcs[w][i] && G.arcs[w][i] < 10000 && !visited[i])
			{
				printf("%c", G.vexs[i]);
				enQueue(&q, i);
				visited[i] = 1;
			}
	}
}

void bfTraverUnaccess(MGraph G, int visited[])
{
	register int i;
	flushVisited(visited, G.vexNum);

	for (i = 0; i < G.vexNum; i++)
		if (!visited[i])
			bfTraver(G, i, visited);

	printf("\n");
}

/////////////////////////////////////////////////

/////////////////////////////////////////////////

/////////////////////////////////////////////////

void floyd(MGraph G)
{
	register int i, j, k, m, n;
	int d[MAXSIZE][MAXSIZE];
	PATHINT path[MAXSIZE][MAXSIZE];
	/*
	初始化path和d
	*/
	for(i = 0; i < G.vexNum; i++)
		for (j = 0; j < G.vexNum; j++)
		{
			d[i][j] = G.arcs[i][j];
			for (k = 0; k < G.vexNum; k++)
				path[i][j][k] = -1;
		}
	/*
	显示d初值
	*/
	printf("\ndist的初值\n");
	for (i = 0; i < G.vexNum; i++)
	{
		for (j = 0; j < G.vexNum; j++)
			printf("%8d|", d[i][j]);
		printf("\n————————————————————\n");
	}
	/*
	存放初始路径
	*/
	for(i = 0; i < G.vexNum; i++)
		for(j = 0; j < G.vexNum; j++)
			if (d[i][j] != 10000 && d[i][j] != 0)
			{
				path[i][j][0] = i;
				path[i][j][1] = j;
			}
	/*
	显示path初值
	*/
	printf("\npath的初值\n");
	for (i = 0; i < G.vexNum; i++)
	{
		for (j = 0; j < G.vexNum; j++)
		{
			for (k = 0; k < G.vexNum; k++)
				printf("%2d,", path[i][j][k]);

			if (k)
				printf("\b");
			printf("|_|");
		}
		printf("\n————————————————————\n");
	}	

	for (k = 0; k < G.vexNum; k++)
	{
		for(i = 0; i <G.vexNum; i++)
			for(j = 0; j < G.vexNum; j++)
				if (d[i][k] + d[k][j] < d[i][j])
				{
					d[i][j] = d[i][k] + d[k][j];
					//将path[i][k]中的路径存入path[i][j]
					for (m = 0; m < G.vexNum && path[i][k][m] != -1; m++)
						path[i][j][m] = path[i][k][m];
					//将path[k][j]中的路径存入path[i][j]
					for (n = 1; n < G.vexNum; m++, n++)
						path[i][j][m] = path[k][j][n];
				}

		printf("\ndist第%d代迭代结果\n", k + 1);
		for (m = 0; m < G.vexNum; m++)
		{
			for (n = 0; n < G.vexNum; n++)
				printf("%8d|", d[m][n]);
			printf("\n————————————————————\n");
		}

		printf("\npath的第%d次迭代结果\n", k + 1);
		for(i = 0; i < G.vexNum; i++)
		{ 
			for (j = 0; j < G.vexNum; j++)
			{
				for (m = 0; m < G.vexNum; m++)
					printf("%2d,", path[i][j][m]);
					
				if (m)
					printf("\b");
				printf("|_|");
			}
			printf("\n————————————————————\n");
		}		
	}//end_for(k...)
}

/*
队列相关操作
*/

int initQueue(Queue* Q)
{
	Q->front = 0;
	Q->rear = 0;
	Q->queueSize = MAXSIZE;
	return 1;
}

int queueEmpty(Queue Q)
{
	if (Q.front == Q.rear)
		return 1;
	else
		return 0;
}

int queueFull(Queue Q)
{
	if ((Q.rear + 1) % Q.queueSize == Q.front)
		return 1;
	else
		return 0;
}

int getHead(Queue Q, int* v)
{
	if (queueEmpty(Q))
		return 0;
	*v = Q.data[Q.front];
	return 1;
}

int enQueue(Queue* Q, int v)
{
	if (queueFull(*Q))
		return 0;
	Q->data[Q->rear] = v;
	Q->rear = (Q->rear + 1) % Q->queueSize;
	return 1;
}

int deQueue(Queue* Q, int* v)
{
	if (queueEmpty(*Q))
		return 0;
	*v = Q->data[Q->front];
	Q->front = (Q->front + 1) % Q->queueSize;
	return 1;
}