#include<stdio.h>
#include<stdlib.h>
#include<string.h>

/***********************************/
/********�������Ͷ�������*********/
/***********************************/

//�����������������
typedef struct tree
{
	char ch;
	struct tree *lchild;
	struct tree *rchild;
}TREE,*BiTree;

//���������������
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

//����ջ��������
typedef struct stack
{
	char ch;
	struct stack *next;
}STACK,*STACKP;

/***********************************/
/**********������������***********/
/***********************************/

//�û�������
void menu(void);																						//��ʾ�˵����
void PrintMenuIntroduction(void);																		//��ʾ�˵�ָ��

//��������ز�����
void initBiTree(BiTree *T);																				//��ʼ��������

void createBiTree_1(BiTree &T);																			//�����ַ�������������
void createBiTree_2(BiTree *T);																			//����ߴ���������

void preorder(BiTree T);																				//�������
void inorder(BiTree T);																					//����
void postorder(BiTree T);																				//����
void layer(BiTree T);																					//���
void dispBiTree(BiTree T,int level,char ch);															//�����չʾ��

void DestroyT(BiTree &T);																				//������
int TEmpty(BiTree T);																					//�ж�����
int depth(BiTree T);																					//��ʾ�����
void leafcount(BiTree T,int *count);																	//��ʾҶ����
void nodecount(BiTree T,int *count);																	//��ʾ�ܽ����
void AllPath(BiTree T,STACKP *S);																		//��ʾȫ��Ҷ�ӽ��·��
void NodePath(BiTree T,STACKP *S,char ch);																//��ʾ��һ���·��

void FindNode(BiTree T,BiTree *bt,char ch);																//�ҵ���һ��㼰���ַָ��
void NodeInsert(BiTree *T,char pa_lrflag,char ch_lrflag,char parent,char child);						//������λ�ò�����
void FindParent(BiTree T,STACKP *S,char child,char *parent);											//�ҵ���һ���ĸ����
void LeaveCut(BiTree *T,STACKP *S,char ch);																//ɾ��Ҷ�ӽ��

//������ز�����
int initQlink(Qlink *Q);																				//��ʼ������
int EnQlink(Qlink *Q,BiTree bt);																		//�����
int DeQlink(Qlink *Q,BiTree *bt);																		//������
int QEmpty(Qlink Q);																					//�ж϶��п�
int GetQlinkHead(Qlink Q,BiTree *bt);																	//�õ�����ͷ
void deleteQlink(Qlink *Q);																				//���ٶ���

//ջ��ز�����
void initStack(STACKP *S);																				//��ʼ��ջ
int StackEmpty(STACKP S);																				//�ж�ջ��
int GetStackTop(STACKP S,char *ch);																		//�õ�ջ��
void push(STACKP *S,char ch);																			//��ջ
int pop(STACKP *S,char *ch);																			//��ջ
void PrintStack(STACKP S);																				//��ӡȫ��ջԪ��
void DestroyStack(STACKP *S);																			//����ջ

///////////////////////////////////////////////////////////////////////////////////////////////////

void main()																								//������
{
	menu();
	system("pause");
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void PrintMenuIntroduction(void)																		//���Ű���ÿ�
{
	printf("////////////////////////////////////////\n");
	printf("///////////////�˵����/////////////////\n");
	printf("////////////////////////////////////////\n");
	printf("\n");

	printf("����1-----������\n");
	printf("����2-----������\n");
	printf("����3-----�������ʾ\n");
	printf("����4-----�޸Ľ����Ϣ\n");
	printf("����5-----��ʾ�����\n");
	printf("����6-----��ʾҶ����\n");
	printf("����7-----��ʾ�������\n");
	printf("����8-----��ʾ���·��\n");
	printf("����9-----���Ҳ����ɾ��\n");

	printf("\n������:");
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void menu(void)																							//�˵����,fflush������ջ�������ֹ����س������߰���Ķ���
{
	int judge1 = 0, count = 0;																				//judge�ж��û�������123�ֱ���[1,2,3����][A,B,C����][Y,N]
	char judge2, judge3, ch, pa_lrflag, ch_lrflag, parent, child;												//countΪ������,��������Ϊ����������,ʹ�ò��ֻ���ע��
	STACKP s;																							//����ջָ��
	BiTree t, bt = NULL;																					//������ָ��

	initBiTree(&t);																						//��ʼ��
	initStack(&s);

	do
	{
		PrintMenuIntroduction();
		fflush(stdin);
		scanf("%d", &judge1);
		fflush(stdin);

		switch (judge1)
		{
		case 1:		printf("����A-----�����ַ���������\n����B-----����ߴ�����\n");					//1.����
			printf("������:");
			fflush(stdin);
			scanf("%c", &judge2);

			if (judge2 == 'A' || judge2 == 'a')
			{
				printf("�����������ַ���:");
				fflush(stdin);
				createBiTree_1(t);
			}

			if (judge2 == 'B' || judge2 == 'b')
			{
				printf("�������:");
				fflush(stdin);
				createBiTree_2(&t);
			}

			break;

		case 2:		
		
		do																				//2.����
		{
			printf("����A-----�������\n����B-----�������\n����C-----�������\n����D-----��α���\n");
			printf("������:");
			fflush(stdin);
			scanf("%c", &judge2);

			switch (judge2)
			{
			case 'A':	if (!TEmpty(t))												//����
				preorder(t);											//�ж����գ���ͬ
					else
				printf("����!");
				printf("\n");
				break;

			case 'B':	if (!TEmpty(t))												//����
				inorder(t);
					else
				printf("����!");
				printf("\n");
				break;

			case 'C':	if (!TEmpty(t))												//����
				postorder(t);
					else
				printf("����!");
				printf("\n");
				break;

			case 'D':	if (!TEmpty(t))												//���
				layer(t);
					else
				printf("����!");
				printf("\n");
				break;
			}//switch_end

			printf("�Ƿ�������б���?\n��-----Y\n��-----N:\n");
			printf("������:");
			fflush(stdin);
			scanf("%c", &judge3);

		} while (judge3 == 'Y' || judge3 == 'y');//while_end

		break;

		case 3:		dispBiTree(t, 1, 'D');															//3.�����ʾ,Ĭ�ϴӸ�����һ�㿪ʼ��ʾ
			break;

		case 4:		printf("������Ҫ�޸ĵĽ����Ϣ:");												//4.�޸Ľ����Ϣ
			fflush(stdin);
			scanf("%c", &ch);

			FindNode(t, &bt, ch);																//�ҵ����

			printf("�������޸ĺ����Ϣ:");
			fflush(stdin);
			scanf("%c", &bt->ch);															//�޸�

			bt = NULL;																		//ָ�����
			break;

		case 5:		printf("�������Ϊ%d\n", depth(t));												//5.����
			break;

		case 6:		leafcount(t, &count);															//6.Ҷ����
			printf("Ҷ����Ϊ%d\n", count);
			count = 0;																		//���������㣬��ͬ
			break;

		case 7:		nodecount(t, &count);															//7.�����
			printf("�������Ϊ%d\n", count);
			count = 0;
			break;

		case 8:		do																				//8.·��
		{
			printf("����A-----�������Ҷ�ӽ��·��\n����B-----�������ָ�����·��\n");
			printf("������:");
			fflush(stdin);
			scanf("%c", &judge2);

			if (judge2 == 'A' || judge2 == 'a')
				AllPath(t, &s);															//�������Ҷ�ӽ��·��
			if (judge2 == 'B' || judge2 == 'b')
			{
				printf("������Ҫ���ҵĽ��:");
				fflush(stdin);
				scanf("%c", &ch);
				NodePath(t, &s, ch);														//���ָ�����·��
			}

			printf("�Ƿ��������·��?\n��-----Y\n��-----N\n");
			printf("������:");
			fflush(stdin);
			scanf("%c", &judge3);

		} while (judge3 == 'Y' || judge3 == 'y');

		break;

		case 9:		do																				//9.����,����,ɾ��
		{
			printf("����A-----���ҽ��\n����B-----������\n����C-----ɾ�����\n");
			printf("������:");
			fflush(stdin);
			scanf("%c", &judge2);

			if (judge2 == 'A' || judge2 == 'a')
			{
				printf("������Ҫ���ҵĽ����Ϣ:");
				fflush(stdin);
				scanf("%c", &ch);

				FindParent(t, &s, ch, &parent);											//�ҵ�ָ����㸸���
				printf("ָ���ڵ�ĸ����Ϊ%c\n", parent);

				FindNode(t, &bt, ch);														//�ҵ�ָ�����
				NodePath(t, &s, ch);														//�ҵ�ָ�����·��
				bt = NULL;																//ָ�����
			}

			if (judge2 == 'B' || judge2 == 'b')
			{
				printf("����������������Ϣ:�²�����ֱ�����Ϊ�����Ѵ��ڵĸ��ӽ�����������������Ϣ(L,R�ֱ������,��)���Լ��²����������ӵ������Ѵ��ڵĸ��ӽ����Ϣ\n");
				printf("������:");
				fflush(stdin);
				scanf("%c%c%c%c", &pa_lrflag, &ch_lrflag, &parent, &child);
				NodeInsert(&t, pa_lrflag, ch_lrflag, parent, child);						//������
			}

			if (judge2 == 'C' || judge2 == 'c')
			{
				printf("����1-----ɾ��Ҷ�ӽ��\n����2-----ɾ��������\n");
				printf("������:");
				fflush(stdin);
				scanf("%d", &judge1);

				if (judge1 == 1)
				{
					printf("������Ҫɾ����Ҷ�ӽ����Ϣ:\n");
					fflush(stdin);
					scanf("%c", &ch);
					LeaveCut(&t, &s, ch);													//ɾ��ָ��Ҷ�ӽ��
				}

				if (judge1 == 2)
				{
					DestroyT(t);														//������
				}
			}

			printf("�Ƿ����Ѱ�Ҳ����ɾ��?\n��-----Y\n��-----N\n");
			printf("������:");
			fflush(stdin);
			scanf("%c", &judge3);

		} while (judge3 == 'Y' || judge3 == 'y');

		break;

		}//switch_end

		printf("�Ƿ��������?\n��-----Y\n��-----N\n");
		printf("������:");
		fflush(stdin);
		scanf("%c", &judge3);
		fflush(stdin);

	} while (judge3 == 'Y' || judge3 == 'y');//while_end

	DestroyT(t);																						//�ܾ�Ұָ�룬��������
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void initBiTree(BiTree* T)
{
	*T = NULL;																							//���ÿ�
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
	char parent, child;																					//���ӽ����
	int lrflag;																							//����������־
	BiTree bt, s;																						//��ָ��
	Qlink q;																							//����ָ��
	initQlink(&q);																						//��ʼ��

	scanf("%c%c%d", &parent, &child, &lrflag);

	while (child != '#')																					//���Ӳ�Ϊ��
	{
		bt = new TREE;
		bt->ch = child;
		bt->lchild = bt->rchild = NULL;																		//����Ҷ�ӽ��
		EnQlink(&q, bt);																					//Ҷ�ӽ�����

		if (parent == '#')																					//�����Ϊ��
			*T = bt;																						//���������

		else
		{
			GetQlinkHead(q, &s);
			while (s->ch != parent)
			{
				DeQlink(&q, &s);
				GetQlinkHead(q, &s);																		//�ڶ������ҵ���һ���ĺ���
			}

			if (lrflag == 0)
				s->lchild = bt;
			else
				s->rchild = bt;																			//������
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

	if (T)																								//�ǿս��
		EnQlink(&q, T);																					//���ָ�������

	while (!QEmpty(q))																					//���в�Ϊ��
	{
		DeQlink(&q, &p);
		printf("%c", p->ch);																				//�����в���ӡ

		if (p->lchild)
			EnQlink(&q, p->lchild);
		if (p->rchild)
			EnQlink(&q, p->rchild);																		//˳������
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void DestroyT(BiTree& T)																				//���ں������ɾ��
{
	if (T)
	{
		DestroyT(T->lchild);
		DestroyT(T->rchild);
		delete T;
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void dispBiTree(BiTree T, int level, char ch)																//levelΪ������ڲ���,chָʾ���Ҹ����
{
	register int i, k;																					//���̱���

	if (T)
	{
		for (i = 1; i < level; i++)																			//���λ��n��,����n��
			putchar(' ');
		printf("%c(%c)+", T->ch, ch);

		for (k = i + 4; k < 20; k++)																				//β�Ͷ೤
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
		depl = depth(T->lchild);																			//���������
		depr = depth(T->rchild);																			//���������

		if (depl > depr)
			return depl + 1;
		else
			return depr + 1;
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void leafcount(BiTree T, int* count)
{
	if (T == NULL)																							//�����򱣳ּ�������ֵ0
		return;

	else
	{
		leafcount(T->lchild, count);																		//������������ļ���
		if (T->lchild == NULL && T->rchild == NULL)															//������Ҷ�ӽ��
			(*count)++;
		leafcount(T->rchild, count);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void nodecount(BiTree T, int* count)																		//˼��ͬ��һ����
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
	char pool;																							//������ñ��������ݳ�

	if (T)																								//�ǿս��
	{
		push(S, T->ch);																					//��ջ

		if (!T->lchild && !T->rchild)																		//����Ҷ�ӽ��
		{
			printf("����Ҷ�ӽ��·��Ϊ:");
			PrintStack(*S);																				//��ӡջ
		}

		else																							//����Ѱ����������
		{
			AllPath(T->lchild, S);
			AllPath(T->rchild, S);
		}

		pop(S, &pool);																					//��ӡ����Ҷ�ӽ���ջ�������ݳ�
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void NodePath(BiTree T, STACKP* S, char ch)																//˼�����ͬ��
{
	char pool;

	if (T)
	{
		push(S, T->ch);

		if (T->ch == ch)																					//�ж�Ҷ�ӽ���Ϊ�жϽ��
		{
			printf("ָ�����·��Ϊ:");
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
	if (T == NULL)																							//��������
		return;

	if (T->ch == ch)																						//�ҵ����
		*bt = T;																							//ָ��ָ��ý��

	else																								//����������������Ѱ��
	{
		FindNode(T->lchild, bt, ch);
		FindNode(T->rchild, bt, ch);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

/**************************************************���Ӻ���������ע��***************************************************/

/**
 *	1.pa_lrflagΪ�����������ڲ����㸸�������������ı�־,ch_lrflagΪ��������ӽ�������ڲ���������������ı�־;
 *	2.parentΪ������ĸ������,childΪ��������ӽ����,����������������ԭ��������Ϊ���ӽ��Ľ��;
 *	3.����˼��:ͨ���ĸ�������ȷ���Ʋ�����λ��,1.�������������¼��ָ�ֵ:'L'������,'R'������,'#'�����;
 *	4.����:��A��B֮�����C,������"LLAB""LRAB""RLAB""RRAB"����ָ��,�ֱ��ʾA��C��B,A��C��B,A��C��B,A��C��B;
 *	5.�������:pa_lrflagΪ'#',�ɽ����¸����,��ѡ��ԭ���������Ϊ�¸�������������; ch_lrflagΪ'#',�ɽ���Ҷ�ӽ�㣬��ѡ����Ҷ�ӽ������Ϊԭ��ĳҶ�ӽڵ�����Һ���;
 *	6.�����������:ԭ�����ΪA,�����¸����B,����"#LAA""#RAA",�ֱ�õ�B��A,B��A;
 *					ԭ��Ҷ�ӽ��C,������Ҷ�ӽ��D,����"L#CC""R#CC",�ֱ�õ�C��D,C��D.
 */

void NodeInsert(BiTree* T, char pa_lrflag, char ch_lrflag, char parent, char child)
{
	BiTree bt, p;

	bt = new TREE;
	printf("�������½����Ϣ:");
	fflush(stdin);
	scanf("%c", &bt->ch);
	bt->lchild = bt->rchild = NULL;																			//�½��������Ҷ�ӽ��

	if (pa_lrflag == '#')																					//�����¸�
	{
		if (ch_lrflag == 'L' || ch_lrflag == 'l')																//ԭ������Ϊ�¸���������
		{
			bt->lchild = (*T);
			*T = bt;																						//�����ָ��ָ���¸����,��ͬ
		}

		if (ch_lrflag == 'R' || ch_lrflag == 'r')																//ԭ������Ϊ�¸���������
		{
			bt->rchild = (*T);
			*T = bt;
		}
	}

	else if (ch_lrflag == '#')																				//�½�Ҷ�ӽ��
	{
		if (pa_lrflag == 'L' || pa_lrflag == 'l')																//��Ҷ��
		{
			FindNode(*T, &p, parent);																		//�ҵ�ԭҶ��,��ͬ
			p->lchild = bt;																				//����,��ͬ
		}

		if (pa_lrflag == 'R' || pa_lrflag == 'r')																//��Ҷ��
		{
			FindNode(*T, &p, parent);
			p->rchild = bt;
		}
	}

	else																								//���������
	{
		if (pa_lrflag == 'L' || pa_lrflag == 'l')																//����Ϊ������������
		{
			if (ch_lrflag == 'L' || ch_lrflag == 'l')															//���ӽ������Ϊ������
			{
				FindNode(*T, &p, child);																	//�ҵ�����,��ͬ
				bt->lchild = p;																			//����,��ͬ
				FindNode(*T, &p, parent);																	//�ҵ�����,��ͬ
				p->lchild = bt;																			//����,��ͬ
			}

			if (ch_lrflag == 'R' || ch_lrflag == 'r')															//���ӽ������Ϊ������
			{
				FindNode(*T, &p, child);
				bt->rchild = p;
				FindNode(*T, &p, parent);
				p->lchild = bt;
			}
		}//if

		if (pa_lrflag == 'R' || pa_lrflag == 'r')																//����Ϊ������������
		{
			if (ch_lrflag == 'L' || ch_lrflag == 'l')															//���ӽ������Ϊ������
			{
				FindNode(*T, &p, child);
				bt->lchild = p;
				FindNode(*T, &p, parent);
				p->rchild = bt;
			}

			if (ch_lrflag == 'R' || ch_lrflag == 'r')															//���ӽ������Ϊ������
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
	if (T)																								//�ǿս��
	{
		push(S, T->ch);																					//��ջ

		if (T->ch == child)																				//�ҵ�����,��ջ����,�ڶ��γ�ջ��Ϊ�丸���
		{
			pop(S, parent);
			pop(S, parent);
		}

		else																							//������������Ѱ��
		{
			FindParent(T->lchild, S, child, parent);
			FindParent(T->rchild, S, child, parent);
		}
	}

	DestroyStack(S);																					//����ջ����Ϊջ�л�ѻ���������
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void LeaveCut(BiTree* T, STACKP* S, char ch)
{
	BiTree p = NULL, bt = NULL;
	char parent;

	FindNode(*T, &p, ch);																					//�ҵ�Ҷ�ӽ��
	FindParent(*T, S, p->ch, &parent);																		//�ҵ�Ҷ�ӽ��ĸ����

	bt = p;																								//��¼��Ҷ�ӽ���ַ
	FindNode(*T, &p, parent);																				//�ҵ�������ַ

	if (p->lchild == bt)																					//��Ҷ����ո����������
		p->lchild = NULL;
	if (p->rchild == bt)																					//��Ҷ����ո����������
		p->rchild = NULL;

	delete bt;																							//ɾ��Ҷ�ӽ��
	bt = NULL;																							//ָ�����
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int initQlink(Qlink* Q)
{
	Q->front = Q->rear = new Qnode;																			//ͷβָ��ָ���½�ͷ���

	if (Q->front == NULL)																					//����ʧ��
		return 0;

	Q->front->next = NULL;																				//ͷ�������ָ���ÿ�
	return 1;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int EnQlink(Qlink* Q, BiTree bt)
{
	QnodeP p;
	p = new Qnode;																						//�³�Ա

	if (p == NULL)
		return 0;

	p->bt = bt;
	p->next = NULL;																						//�����Ա��Ϣ,��Ա����ָ���ÿ�
	Q->rear->next = p;																					//���ӵ�ͷ����
	Q->rear = p;																							//����β���

	return 1;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int DeQlink(Qlink* Q, BiTree* bt)
{
	QnodeP p;

	if (Q->rear == Q->front)																				//���п�
		return 0;

	*bt = Q->front->next->bt;																				//ȡ��ͷ��ԱԪ��
	p = Q->front->next;																					//ָ��ָ���ͷ��Ա
	Q->front->next = p->next;																				//ͷ������ӵ���һ��Ա

	if (Q->rear == p)
		Q->rear = Q->front;																				//ֻ��һ����Ա,ͷβָ��ָ��ͷ���
	delete p;																							//ɾ�����ӳ�Ա

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

	if (!QEmpty(*Q))																					//���в�Ϊ�գ�����������
		while (Q->front != Q->rear)
			DeQlink(Q, &btpool);
	else
		delete Q->front;																				//����Ϊ�գ�ɾ��ͷ���

	delete btpool;																						//������ݳ�
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void initStack(STACKP* S)
{
	*S = NULL;																							//ջ���ÿ�
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
	p->next = *S;																							//������Ԫ��
	*S = p;																								//����ջ��
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int pop(STACKP* S, char* ch)
{
	STACKP p = *S;																						//��¼ջ��

	if (*S == NULL)																						//ջ��
		return 0;

	*S = (*S)->next;																						//ջ������һ��
	*ch = p->ch;																							//ȡԭջ��Ԫ��
	delete p;																							//ɾ��ԭջ��

	return 1;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void PrintStack(STACKP S)
{
	STACKP p = S;

	if (S == NULL)
		printf("ջ��!");

	else
		while (p != NULL)
		{
			printf("%c", p->ch);

			if (p->next)																					//��һ����㲻Ϊ��,��ӡ���ӷ�
				printf("-->");

			p = p->next;
		}

	printf("\n");
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void DestroyStack(STACKP* S)
{
	STACKP p;
	p = *S;																								//��¼ջ��

	while (!StackEmpty(*S))																				//�ǿ�ջ
	{
		*S = (*S)->next;
		delete p;
		p = *S;																							//����¼ɾ��
	}
}