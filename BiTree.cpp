#include<stdio.h>
#include<stdlib.h>
#include<string.h>

/***********************************/
/********数据类型定义代码块*********/
/***********************************/

//定义二叉树数据类型
typedef struct tree
{
	char ch;
	struct tree *lchild;
	struct tree *rchild;
}TREE,*BiTree;

//定义队列数据类型
typedef struct queue
{
	BiTree bt;
	struct queue *next;
}Qnode,*QnodeP;

typedef struct
{
	QnodeP front;
	QnodeP rear;
}Qlink;

//定义栈数据类型
typedef struct stack
{
	char ch;
	struct stack *next;
}STACK,*STACKP;

/***********************************/
/**********函数定义代码块***********/
/***********************************/

//用户操作类
void menu(void);																						//显示菜单面板
void PrintMenuIntroduction(void);																		//显示菜单指引

//二叉树相关操作类
void initBiTree(BiTree *T);																				//初始化二叉树

void createBiTree_1(BiTree &T);																			//先序字符串创建二叉树
void createBiTree_2(BiTree *T);																			//读入边创建二叉树

void preorder(BiTree T);																				//先序遍历
void inorder(BiTree T);																					//中序
void postorder(BiTree T);																				//后序
void layer(BiTree T);																					//层次
void dispBiTree(BiTree T,int level,char ch);															//凹入表展示树

void DestroyT(BiTree &T);																				//销毁树
int TEmpty(BiTree T);																					//判断树空
int depth(BiTree T);																					//显示树深度
void leafcount(BiTree T,int *count);																	//显示叶子数
void nodecount(BiTree T,int *count);																	//显示总结点数
void AllPath(BiTree T,STACKP *S);																		//显示全部叶子结点路径
void NodePath(BiTree T,STACKP *S,char ch);																//显示任一结点路径

void FindNode(BiTree T,BiTree *bt,char ch);																//找到任一结点及其地址指针
void NodeInsert(BiTree *T,char pa_lrflag,char ch_lrflag,char parent,char child);						//在任意位置插入结点
void FindParent(BiTree T,STACKP *S,char child,char *parent);											//找到任一结点的父结点
void LeaveCut(BiTree *T,STACKP *S,char ch);																//删除叶子结点

//队列相关操作类
int initQlink(Qlink *Q);																				//初始化队列
int EnQlink(Qlink *Q,BiTree bt);																		//入队列
int DeQlink(Qlink *Q,BiTree *bt);																		//出队列
int QEmpty(Qlink Q);																					//判断队列空
int GetQlinkHead(Qlink Q,BiTree *bt);																	//得到队列头
void deleteQlink(Qlink *Q);																				//销毁队列

//栈相关操作类
void initStack(STACKP *S);																				//初始化栈
int StackEmpty(STACKP S);																				//判断栈空
int GetStackTop(STACKP S,char *ch);																		//得到栈顶
void push(STACKP *S,char ch);																			//入栈
int pop(STACKP *S,char *ch);																			//出栈
void PrintStack(STACKP S);																				//打印全部栈元素
void DestroyStack(STACKP *S);																			//销毁栈

///////////////////////////////////////////////////////////////////////////////////////////////////

void main()																								//主函数
{
	menu();
	system("pause");
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void PrintMenuIntroduction(void)																		//我排版真好看
{
	printf("////////////////////////////////////////\n");
	printf("///////////////菜单面板/////////////////\n");
	printf("////////////////////////////////////////\n");
	printf("\n");

	printf("输入1-----创建树\n");
	printf("输入2-----遍历树\n");
	printf("输入3-----凹入表显示\n");
	printf("输入4-----修改结点信息\n");
	printf("输入5-----显示树深度\n");
	printf("输入6-----显示叶子数\n");
	printf("输入7-----显示结点总数\n");
	printf("输入8-----显示结点路径\n");
	printf("输入9-----查找插入和删除\n");

	printf("\n请输入:");
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void menu(void)																							//菜单面板,fflush都是清空缓存区防止输入回车等乱七八糟的东西
{
	int judge1 = 0, count = 0;																				//judge判断用户操作，123分别存放[1,2,3……][A,B,C……][Y,N]
	char judge2, judge3, ch, pa_lrflag, ch_lrflag, parent, child;												//count为计数器,其他变量为待操作变量,使用部分会做注释
	STACKP s;																							//定义栈指针
	BiTree t, bt = NULL;																					//定义树指针

	initBiTree(&t);																						//初始化
	initStack(&s);

	do
	{
		PrintMenuIntroduction();
		fflush(stdin);
		scanf("%d", &judge1);
		fflush(stdin);

		switch (judge1)
		{
		case 1:		printf("输入A-----先序字符串创建树\n输入B-----读入边创建树\n");					//1.创建
			printf("请输入:");
			fflush(stdin);
			scanf("%c", &judge2);

			if (judge2 == 'A' || judge2 == 'a')
			{
				printf("请输入先序字符串:");
				fflush(stdin);
				createBiTree_1(t);
			}

			if (judge2 == 'B' || judge2 == 'b')
			{
				printf("请输入边:");
				fflush(stdin);
				createBiTree_2(&t);
			}

			break;

		case 2:		
		
		do																				//2.遍历
		{
			printf("输入A-----先序遍历\n输入B-----中序遍历\n输入C-----后序遍历\n输入D-----层次遍历\n");
			printf("请输入:");
			fflush(stdin);
			scanf("%c", &judge2);

			switch (judge2)
			{
			case 'A':	if (!TEmpty(t))												//先序
				preorder(t);											//判断树空，下同
					else
				printf("空树!");
				printf("\n");
				break;

			case 'B':	if (!TEmpty(t))												//中序
				inorder(t);
					else
				printf("空树!");
				printf("\n");
				break;

			case 'C':	if (!TEmpty(t))												//后序
				postorder(t);
					else
				printf("空树!");
				printf("\n");
				break;

			case 'D':	if (!TEmpty(t))												//层次
				layer(t);
					else
				printf("空树!");
				printf("\n");
				break;
			}//switch_end

			printf("是否继续进行遍历?\n是-----Y\n否-----N:\n");
			printf("请输入:");
			fflush(stdin);
			scanf("%c", &judge3);

		} while (judge3 == 'Y' || judge3 == 'y');//while_end

		break;

		case 3:		dispBiTree(t, 1, 'D');															//3.凹入表示,默认从根结点第一层开始表示
			break;

		case 4:		printf("请输入要修改的结点信息:");												//4.修改结点信息
			fflush(stdin);
			scanf("%c", &ch);

			FindNode(t, &bt, ch);																//找到结点

			printf("请输入修改后的信息:");
			fflush(stdin);
			scanf("%c", &bt->ch);															//修改

			bt = NULL;																		//指针归零
			break;

		case 5:		printf("树的深度为%d\n", depth(t));												//5.树深
			break;

		case 6:		leafcount(t, &count);															//6.叶子数
			printf("叶子数为%d\n", count);
			count = 0;																		//计数器归零，下同
			break;

		case 7:		nodecount(t, &count);															//7.结点数
			printf("结点总数为%d\n", count);
			count = 0;
			break;

		case 8:		do																				//8.路径
		{
			printf("输入A-----输出所有叶子结点路径\n输入B-----输出任意指定结点路径\n");
			printf("请输入:");
			fflush(stdin);
			scanf("%c", &judge2);

			if (judge2 == 'A' || judge2 == 'a')
				AllPath(t, &s);															//输出所有叶子结点路径
			if (judge2 == 'B' || judge2 == 'b')
			{
				printf("请输入要查找的结点:");
				fflush(stdin);
				scanf("%c", &ch);
				NodePath(t, &s, ch);														//输出指定结点路径
			}

			printf("是否继续查找路径?\n是-----Y\n否-----N\n");
			printf("请输入:");
			fflush(stdin);
			scanf("%c", &judge3);

		} while (judge3 == 'Y' || judge3 == 'y');

		break;

		case 9:		do																				//9.查找,插入,删除
		{
			printf("输入A-----查找结点\n输入B-----插入结点\n输入C-----删除结点\n");
			printf("请输入:");
			fflush(stdin);
			scanf("%c", &judge2);

			if (judge2 == 'A' || judge2 == 'a')
			{
				printf("请输入要查找的结点信息:");
				fflush(stdin);
				scanf("%c", &ch);

				FindParent(t, &s, ch, &parent);											//找到指定结点父结点
				printf("指定节点的父结点为%c\n", parent);

				FindNode(t, &bt, ch);														//找到指定结点
				NodePath(t, &s, ch);														//找到指定结点路径
				bt = NULL;																//指针归零
			}

			if (judge2 == 'B' || judge2 == 'b')
			{
				printf("请依次输入以下信息:新插入结点分别连接为树中已存在的父子结点的左子树右子树信息(L,R分别代表左,右)，以及新插入结点所连接的树中已存在的父子结点信息\n");
				printf("请输入:");
				fflush(stdin);
				scanf("%c%c%c%c", &pa_lrflag, &ch_lrflag, &parent, &child);
				NodeInsert(&t, pa_lrflag, ch_lrflag, parent, child);						//插入结点
			}

			if (judge2 == 'C' || judge2 == 'c')
			{
				printf("输入1-----删除叶子结点\n输入2-----删除整棵树\n");
				printf("请输入:");
				fflush(stdin);
				scanf("%d", &judge1);

				if (judge1 == 1)
				{
					printf("请输入要删除的叶子结点信息:\n");
					fflush(stdin);
					scanf("%c", &ch);
					LeaveCut(&t, &s, ch);													//删除指定叶子结点
				}

				if (judge1 == 2)
				{
					DestroyT(t);														//销毁树
				}
			}

			printf("是否继续寻找插入和删除?\n是-----Y\n否-----N\n");
			printf("请输入:");
			fflush(stdin);
			scanf("%c", &judge3);

		} while (judge3 == 'Y' || judge3 == 'y');

		break;

		}//switch_end

		printf("是否继续操作?\n是-----Y\n否-----N\n");
		printf("请输入:");
		fflush(stdin);
		scanf("%c", &judge3);
		fflush(stdin);

	} while (judge3 == 'Y' || judge3 == 'y');//while_end

	DestroyT(t);																						//拒绝野指针，从我做起
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void initBiTree(BiTree* T)
{
	*T = NULL;																							//根置空
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void createBiTree_1(BiTree& T)
{
	char ch;
	scanf("%c", &ch);

	if (ch == '#')
		T = NULL;

	else
	{
		T = new TREE;
		T->ch = ch;
		createBiTree_1(T->lchild);
		createBiTree_1(T->rchild);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void createBiTree_2(BiTree* T)
{
	char parent, child;																					//父子结点名
	int lrflag;																							//左右子树标志
	BiTree bt, s;																						//树指针
	Qlink q;																							//队列指针
	initQlink(&q);																						//初始化

	scanf("%c%c%d", &parent, &child, &lrflag);

	while (child != '#')																					//孩子不为空
	{
		bt = new TREE;
		bt->ch = child;
		bt->lchild = bt->rchild = NULL;																		//建立叶子结点
		EnQlink(&q, bt);																					//叶子进队列

		if (parent == '#')																					//父结点为空
			*T = bt;																						//建立根结点

		else
		{
			GetQlinkHead(q, &s);
			while (s->ch != parent)
			{
				DeQlink(&q, &s);
				GetQlinkHead(q, &s);																		//在队列中找到上一结点的孩子
			}

			if (lrflag == 0)
				s->lchild = bt;
			else
				s->rchild = bt;																			//建立边
		}
		fflush(stdin);
		scanf("%c%c%d", &parent, &child, &lrflag);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void preorder(BiTree T)
{
	if (T)
	{
		printf("%c", T->ch);
		preorder(T->lchild);
		preorder(T->rchild);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void inorder(BiTree T)
{
	if (T)
	{
		inorder(T->lchild);
		printf("%c", T->ch);
		inorder(T->rchild);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void postorder(BiTree T)
{
	if (T)
	{
		postorder(T->lchild);
		postorder(T->rchild);
		printf("%c", T->ch);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void layer(BiTree T)
{
	BiTree p = NULL;
	Qlink q;
	initQlink(&q);

	if (T)																								//非空结点
		EnQlink(&q, T);																					//结点指针入队列

	while (!QEmpty(q))																					//队列不为空
	{
		DeQlink(&q, &p);
		printf("%c", p->ch);																				//出队列并打印

		if (p->lchild)
			EnQlink(&q, p->lchild);
		if (p->rchild)
			EnQlink(&q, p->rchild);																		//顺藤摸瓜
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void DestroyT(BiTree& T)																				//基于后序遍历删除
{
	if (T)
	{
		DestroyT(T->lchild);
		DestroyT(T->rchild);
		delete T;
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void dispBiTree(BiTree T, int level, char ch)																//level为结点所在层数,ch指示左右根结点
{
	register int i, k;																					//过程变量

	if (T)
	{
		for (i = 1; i < level; i++)																			//结点位于n层,缩进n格
			putchar(' ');
		printf("%c(%c)+", T->ch, ch);

		for (k = i + 4; k < 20; k++)																				//尾巴多长
			putchar('-');
		putchar('\n');

		dispBiTree(T->lchild, level + 1, 'L');
		dispBiTree(T->rchild, level + 1, 'R');
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int TEmpty(BiTree T)
{
	if (T)
		return 0;
	else
		return 1;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int depth(BiTree T)
{
	int depl, depr;

	if (T == NULL)
		return 0;

	else
	{
		depl = depth(T->lchild);																			//左子树深度
		depr = depth(T->rchild);																			//右子树深度

		if (depl > depr)
			return depl + 1;
		else
			return depr + 1;
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void leafcount(BiTree T, int* count)
{
	if (T == NULL)																							//空树则保持计数器初值0
		return;

	else
	{
		leafcount(T->lchild, count);																		//基于中序遍历的计数
		if (T->lchild == NULL && T->rchild == NULL)															//若遇到叶子结点
			(*count)++;
		leafcount(T->rchild, count);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void nodecount(BiTree T, int* count)																		//思想同上一函数
{
	if (T == NULL)
		return;

	else
	{
		nodecount(T->lchild, count);
		(*count)++;
		nodecount(T->rchild, count);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void AllPath(BiTree T, STACKP* S)
{
	char pool;																							//存放无用变量的数据池

	if (T)																								//非空结点
	{
		push(S, T->ch);																					//入栈

		if (!T->lchild && !T->rchild)																		//遇到叶子结点
		{
			printf("所有叶子结点路径为:");
			PrintStack(*S);																				//打印栈
		}

		else																							//否则寻找左右子树
		{
			AllPath(T->lchild, S);
			AllPath(T->rchild, S);
		}

		pop(S, &pool);																					//打印过的叶子结点出栈流入数据池
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void NodePath(BiTree T, STACKP* S, char ch)																//思想基本同上
{
	char pool;

	if (T)
	{
		push(S, T->ch);

		if (T->ch == ch)																					//判断叶子结点改为判断结点
		{
			printf("指定结点路径为:");
			PrintStack(*S);
		}

		else
		{
			NodePath(T->lchild, S, ch);
			NodePath(T->rchild, S, ch);
		}

		pop(S, &pool);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void FindNode(BiTree T, BiTree* bt, char ch)
{
	if (T == NULL)																							//空树返回
		return;

	if (T->ch == ch)																						//找到结点
		*bt = T;																							//指针指向该结点

	else																								//在左子树右子树上寻找
	{
		FindNode(T->lchild, bt, ch);
		FindNode(T->rchild, bt, ch);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

/**************************************************复杂函数，单独注释***************************************************/

/**
 *	1.pa_lrflag为插入结点连接于插入结点父结点的左右子树的标志,ch_lrflag为插入结点的子结点连接于插入结点的左右子树的标志;
 *	2.parent为插入结点的父结点名,child为插入结点的子结点名,这两个结点必须是在原二叉树中为父子结点的结点;
 *	3.基本思想:通过四个变量精确控制插入结点位置,1.中两变量有如下几种赋值:'L'代表左,'R'代表右,'#'代表空;
 *	4.举例:在A与B之间插入C,可输入"LLAB""LRAB""RLAB""RRAB"四种指令,分别表示A左C左B,A左C右B,A右C左B,A右C右B;
 *	5.特殊情况:pa_lrflag为'#',可建立新根结点,并选择将原根结点链接为新根结点的左右子树; ch_lrflag为'#',可建立叶子结点，并选择将新叶子结点链接为原树某叶子节点的左右孩子;
 *	6.特殊情况举例:原根结点为A,建立新根结点B,输入"#LAA""#RAA",分别得到B左A,B右A;
 *					原有叶子结点C,建立新叶子结点D,输入"L#CC""R#CC",分别得到C左D,C右D.
 */

void NodeInsert(BiTree* T, char pa_lrflag, char ch_lrflag, char parent, char child)
{
	BiTree bt, p;

	bt = new TREE;
	printf("请输入新结点信息:");
	fflush(stdin);
	scanf("%c", &bt->ch);
	bt->lchild = bt->rchild = NULL;																			//新建结点做成叶子结点

	if (pa_lrflag == '#')																					//创建新根
	{
		if (ch_lrflag == 'L' || ch_lrflag == 'l')																//原根链接为新根的左子树
		{
			bt->lchild = (*T);
			*T = bt;																						//根结点指针指向新根结点,下同
		}

		if (ch_lrflag == 'R' || ch_lrflag == 'r')																//原根链接为新根的右子树
		{
			bt->rchild = (*T);
			*T = bt;
		}
	}

	else if (ch_lrflag == '#')																				//新建叶子结点
	{
		if (pa_lrflag == 'L' || pa_lrflag == 'l')																//左叶子
		{
			FindNode(*T, &p, parent);																		//找到原叶子,下同
			p->lchild = bt;																				//链接,下同
		}

		if (pa_lrflag == 'R' || pa_lrflag == 'r')																//右叶子
		{
			FindNode(*T, &p, parent);
			p->rchild = bt;
		}
	}

	else																								//非特殊情况
	{
		if (pa_lrflag == 'L' || pa_lrflag == 'l')																//链接为父结点的左子树
		{
			if (ch_lrflag == 'L' || ch_lrflag == 'l')															//孩子结点链接为左子树
			{
				FindNode(*T, &p, child);																	//找到孩子,下同
				bt->lchild = p;																			//链接,下同
				FindNode(*T, &p, parent);																	//找到父亲,下同
				p->lchild = bt;																			//链接,下同
			}

			if (ch_lrflag == 'R' || ch_lrflag == 'r')															//孩子结点链接为右子树
			{
				FindNode(*T, &p, child);
				bt->rchild = p;
				FindNode(*T, &p, parent);
				p->lchild = bt;
			}
		}//if

		if (pa_lrflag == 'R' || pa_lrflag == 'r')																//链接为父结点的右子树
		{
			if (ch_lrflag == 'L' || ch_lrflag == 'l')															//孩子结点链接为左子树
			{
				FindNode(*T, &p, child);
				bt->lchild = p;
				FindNode(*T, &p, parent);
				p->rchild = bt;
			}

			if (ch_lrflag == 'R' || ch_lrflag == 'r')															//孩子结点链接为右子树
			{
				FindNode(*T, &p, child);
				bt->rchild = p;
				FindNode(*T, &p, parent);
				p->rchild = bt;
			}
		}//if
	}//else

}

///////////////////////////////////////////////////////////////////////////////////////////////////

void FindParent(BiTree T, STACKP* S, char child, char* parent)
{
	if (T)																								//非空结点
	{
		push(S, T->ch);																					//入栈

		if (T->ch == child)																				//找到孩子,出栈两次,第二次出栈即为其父结点
		{
			pop(S, parent);
			pop(S, parent);
		}

		else																							//在左右子树上寻找
		{
			FindParent(T->lchild, S, child, parent);
			FindParent(T->rchild, S, child, parent);
		}
	}

	DestroyStack(S);																					//销毁栈，因为栈中会堆积无用数据
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void LeaveCut(BiTree* T, STACKP* S, char ch)
{
	BiTree p = NULL, bt = NULL;
	char parent;

	FindNode(*T, &p, ch);																					//找到叶子结点
	FindParent(*T, S, p->ch, &parent);																		//找到叶子结点的父结点

	bt = p;																								//记录该叶子结点地址
	FindNode(*T, &p, parent);																				//找到父结点地址

	if (p->lchild == bt)																					//左叶子清空父结点左子树
		p->lchild = NULL;
	if (p->rchild == bt)																					//右叶子清空父结点右子树
		p->rchild = NULL;

	delete bt;																							//删除叶子结点
	bt = NULL;																							//指针归零
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int initQlink(Qlink* Q)
{
	Q->front = Q->rear = new Qnode;																			//头尾指针指向新建头结点

	if (Q->front == NULL)																					//申请失败
		return 0;

	Q->front->next = NULL;																				//头结点链接指针置空
	return 1;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int EnQlink(Qlink* Q, BiTree bt)
{
	QnodeP p;
	p = new Qnode;																						//新成员

	if (p == NULL)
		return 0;

	p->bt = bt;
	p->next = NULL;																						//键入成员信息,成员链接指针置空
	Q->rear->next = p;																					//链接到头结点后
	Q->rear = p;																							//重置尾结点

	return 1;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int DeQlink(Qlink* Q, BiTree* bt)
{
	QnodeP p;

	if (Q->rear == Q->front)																				//队列空
		return 0;

	*bt = Q->front->next->bt;																				//取队头成员元素
	p = Q->front->next;																					//指针指向队头成员
	Q->front->next = p->next;																				//头结点连接到下一成员

	if (Q->rear == p)
		Q->rear = Q->front;																				//只有一个成员,头尾指针指向头结点
	delete p;																							//删除出队成员

	return 1;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int QEmpty(Qlink Q)
{
	if (Q.front == Q.rear)
		return 1;
	else
		return 0;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int GetQlinkHead(Qlink Q, BiTree* bt)
{
	if (Q.front == Q.rear)
		return 0;

	*bt = Q.front->next->bt;
	return 1;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void deleteQlink(Qlink* Q)
{
	BiTree btpool;

	if (!QEmpty(*Q))																					//队列不为空，连续出队列
		while (Q->front != Q->rear)
			DeQlink(Q, &btpool);
	else
		delete Q->front;																				//队列为空，删除头结点

	delete btpool;																						//清空数据池
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void initStack(STACKP* S)
{
	*S = NULL;																							//栈顶置空
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int StackEmpty(STACKP S)
{
	if (S == NULL)
		return 1;
	else
		return 0;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int GetStackTop(STACKP S, char* ch)
{
	if (S == NULL)
		return 0;

	*ch = S->ch;
	return 1;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void push(STACKP* S, char ch)
{
	STACKP p = new STACK;

	p->ch = ch;
	p->next = *S;																							//链接新元素
	*S = p;																								//重置栈顶
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int pop(STACKP* S, char* ch)
{
	STACKP p = *S;																						//记录栈顶

	if (*S == NULL)																						//栈空
		return 0;

	*S = (*S)->next;																						//栈顶下移一层
	*ch = p->ch;																							//取原栈顶元素
	delete p;																							//删除原栈顶

	return 1;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void PrintStack(STACKP S)
{
	STACKP p = S;

	if (S == NULL)
		printf("栈空!");

	else
		while (p != NULL)
		{
			printf("%c", p->ch);

			if (p->next)																					//下一个结点不为空,打印连接符
				printf("-->");

			p = p->next;
		}

	printf("\n");
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void DestroyStack(STACKP* S)
{
	STACKP p;
	p = *S;																								//记录栈顶

	while (!StackEmpty(*S))																				//非空栈
	{
		*S = (*S)->next;
		delete p;
		p = *S;																							//逐层记录删除
	}
}