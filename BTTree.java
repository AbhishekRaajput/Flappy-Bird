import java.util.ArrayList;
import java.util.LinkedList;
public class BTTree{
    public static void main(String[] args){
        // int[] arr={10,20,40,80,-1,-1,90,-1,-1,50,100,-1,-1,-1,30,60,110,-1,-1,-1,70,120,-1,-1,-1};
        // BTTree bt=new BTTree(arr);
        // bt.display();

        // System.out.println(bt.size(bt.root));
        // System.out.println(bt.height(bt.root));
        // bt.Pre(bt.root);
        // System.out.println();
        // bt.In(bt.root);
        // System.out.println();
        // bt.Post(bt.root);
        // System.out.println();
        // System.out.println(bt.min(bt.root));
        // System.out.println(bt.max(bt.root));
        // System.out.println(bt.find(bt.root,50));
        // ArrayList <Node> path = new ArrayList<>();
        // System.out.println(bt.rootToNodePath01(bt.root,50, path));
        //bt.leafNodes(bt.root);
        //bt.levelOrder(bt.root);
        // bt.levelOrder_01(bt.root);
        // bt.levelOrder_02(bt.root);
        //bt.WavePrint(bt.root);
        // ArrayList <Node> ans = bt.rootToNodePath(root,80);
        // for(Node node : ans){
        //     System.out.print(node.data + " ");
        // }
        // System.out.println();
        // bt.leafNodes(bt.root);
        int[] pre = {10, 20, 40, 50, 60, 30, 70, 90, 80};
        int[] in = {40, 20, 60, 50, 10, 70, 90, 30, 80};
        Node node=preIn(pre,in,0,8,0,8);
        display(node);
    }
    public static class Node{
        public int data=0;
        public Node left=null;
        public Node right=null;
         
        Node(int data){
            this.data=data;
        }
        Node(int data,Node left,Node right){
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }
    static Node root=null;
    public BTTree(int[] arr){
        root=construct(arr);
    }
    static int idx=0;
    public Node construct(int[]arr){
        if(idx>=arr.length || arr[idx]==-1){
            idx++;
            return null;
        }
        Node node=new Node(arr[idx],null,null);
        idx++;
        node.left=(construct(arr));
        node.right=(construct(arr));

        return node;
    }
    // public void display(){
    //     display(this.root);
    // }
    public static void display(Node node){
        if(node==null)
           return;    
        StringBuilder sb=new StringBuilder();
        sb.append(node.left!=null?node.left.data:".");
        sb.append("->" + node.data+ "<-");
        sb.append(node.right!=null?node.right.data:".");

        System.out.println(sb.toString());
        display(node.left);
        display(node.right);  
    }  
    
    public static int size(Node node){
        if(node==null){
            return 0;
        }
        int s=0;
        s+=size(node.left);
        s+=size(node.right);
        return s+1;
    }
    public static int height(Node node){
        if(node==null){
            return 0;
        }
        int l=0;
        int r=0;
        l+=height(node.left);
        r+=height(node.right);
        return Math.max(l,r)+1;
    } 
    public static void Pre(Node node){
        if(node==null){
            return ;
        }
        System.out.print(node.data+" ");
        Pre(node.left);
        Pre(node.right);
    }
    public static void In(Node node){
        if(node==null){
            return;
        }
        In(node.left);
        System.out.print(node.data+" ");
        In(node.right);
    }
    
    public static void Post(Node node){
        if(node==null){
            return ;
        }
        Post(node.left);
        Post(node.right);
        System.out.print(node.data+" ");
   }
   public static boolean find(Node node,int data){
       if(node==null){
           return false;
       }
       if(node.data==data){
           return true;
       } 
       find(node.left);
       find(node.right);
   }
   public static int max(Node node){
       if(node==null){
           return Integer.MIN_VALUE;
       }
       int maxl=max(node.left);
       int maxr=max(node.right);
       return Math.max(node.data,Math.max(maxr,maxl));

   }
   public static int min(Node node){
       if(node==null){
           return Integer.MAX_VALUE;
       }
       int minl=min(node.left);
       int minr=min(node.right);
       int minroot=Math.min(minl,minr);
       return Math.min(node.data,minroot);
    }
    public static ArrayList<Node> rootToNodePath(Node node,int data){
        if(node==null){
            return null;
        }
        if(node.data==data){
            ArrayList<Node>base=new ArrayList<>();
            base.add(node);
            return base;
        }
        ArrayList<Node>leftans=rootToNodePath(node.left,data);
        if(leftans!=null){
            leftans.add(node);
            return leftans;
        }
        ArrayList<Node>rightans=rootToNodePath(node.right,data);
        if(rightans!=null){
        return rightans;
        }
        return null;
    }
    public static boolean rootToNodePath01(Node root,int data, ArrayList<Node> path){
        if(root==null){
            return false;
        }
        if(root.data==data){
            path.add(root);
            return true;
        }
        if(rootToNodePath01(root.left,data,path)){
            path.add(root);
            return true;
        }
        if(rootToNodePath01(root.right,data,path)){
            path.add(root);
            return true;
        }
            return false;
    }
    public static void leafNodes(Node node){
        if (node==null){
            return;
        }
        if(node.left==null && node.right==null){
            System.out.print(node.data+" ");
        }
        leafNodes(node.left);
        leafNodes(node.right);
    }
    public static void levelOrder(Node root){
        LinkedList<Node>queue=new LinkedList<>();
        queue.addLast(root);
        while(queue.size()>0){
            Node node=queue.removeFirst();
            System.out.print(node.data+" ");
            if(node.left!=null){
                queue.addLast(node.left);
            }
            if(node.right!=null){
                queue.addLast(node.right);
            }
        }
    }
    public static void levelOrder_01(Node root){
        LinkedList<Node>que=new LinkedList<>();
        que.addLast(root);
        while(que.size()>0){
            int size=que.size();
            while(size-->0){
                Node node=que.removeFirst();
                System.out.print(node.data+" ");
                if(node.left!=null){
                    que.addLast(node.left);
                }
                if(node.right!=null){
                    que.addLast(node.right);
                }
            }
            System.out.println();
        }
    }
    public static void levelOrder_02(Node root){  // Using 2 Ques: Here We are storing the parent
        LinkedList<Node>que1=new LinkedList<>();  // in Queue1 and its children in Queue2.
        LinkedList<Node>que2=new LinkedList<>();  // Then storing elements of Queue1 in a node and
        que1.addLast(root);                       // printing them simultaneously .
        while(que1.size()>0){                     // When Que1 becomes empty we swap the ques and
            Node node=que1.removeFirst();         // do the same.
            System.out.print(node.data+" ");
            if(node.left!=null){
                que2.addLast(node.left);
            }
            if(node.right!=null){
                que2.addLast(node.right);
            }
            if(que1.size()==0){
                System.out.println();
                LinkedList<Node>que3=new LinkedList<>();
                que3=que1;
                que1=que2;
                que2=que3;
            }
        }
    }
    public static void WavePrint(Node root){
        LinkedList<Node>que1=new LinkedList<>();
        LinkedList<Node>que2=new LinkedList<>();
        que1.addLast(root);
        int flag=0;
        while(que1.size()>0){
            Node node=que1.removeFirst();
            System.out.print(node.data+" ");
            if(flag%2==0){
                if(node.left!=null){
                    que2.addLast(node.left);
                }
                if(node.right!=null){
                    que2.addLast(node.right);
                }
            }
            else{
                if(node.right!=null){
                    que2.addLast(node.right);
                }
                if(node.left!=null){
                    que2.addLast(node.left);
                }
            }
            if(que1.size()==0){
                //System.out.println();
                LinkedList<Node>que3=new LinkedList<>();
                que3=que1;
                que1=que2;
                que2=que3;
                flag++;
            }
        }

    }
    // public static void LCA(Node root,int data1,int data2){
    //     ArrayList<Node>path1=rootToNodePath(root,data1);
    //     ArrayList<Node>path2=rootToNodePath(root,data2);
    //     if(path1==null||path2==null){
    //         return null;
    //     }
    //     int i=path1.length()-1;
    //     int j=path2.length()-1;
    //     Node ans=null;
    //     while(i>=0&&j>=0){
    //         if(path1.get(i).data==path2.get(j).data){
    //             ans=path1.get(i);
    //         }
    //         else{
    //             break;
    //         }
    //         i--;
    //         j--;
    //     }
    //}
    // Node LCA=null;
    // public static void LCA_02(Node node,int data1,int data2){
    //     if(node==null){
    //         return false;
    //     }
    //     boolean selfdone=node.data==data1||node.data==data2;
    //     boolean left=LCA_02(node.left,data);
    //     boolean right=LCA_02(node.right,data);
    //     if((left && right)||(left && selfdone)||(right&& selfdone))
    //     LCA=node;
    //     return left||right||selfdone;      
    // }
    static int prev=Integer.MIN_VALUE;
    public static boolean isBST(Node curr){
        if(curr==null){
          return true;
        }
    boolean left=isBST(curr.left);
    if(!left){
        return false;
    }
    if(prev<curr.data){
        prev=curr.data;
    }
    else{
        return false;
    }
    boolean right=isBST(curr.right);
    if(!right){
        return false;
    }
    return true;
  }
  public static int diameter_01(Node node){
      if(node==null){
          return 0;
      }
      int lh=height(node.left);
      int rh=height(node.right);

      int ld=diameter_01(node.left);
      int hd=diameter_01(node.right);
      return Math.max(Math.max(ld,hd),lh+rh+1);
  }
//   public static void deleteleaf_01(Node node,int leaf){
//       if(node==null){
//           return;
//       }
//       if(node.data)
//   }
  public static Node preIn(int []pre,int [] in,int ps,int pe,int is,int ie){
      if(ps>pe||is>ie){
          return null;
      }
      Node node=new Node(pre[ps]);
      int idx=is;
      while(idx<=ie){
          if(in[idx]==pre[ps]){
          break;
          }  
      idx++;
      }
    int tne=idx-is;

    node.left=preIn(pre,in,ps+1,ps+tne,is,idx-1);
    node.right=preIn(pre,in,ps+tne+1,pe,idx+1,ie);

    return node;
  }
//   public static Node postIn(int []post,int []in, int ){
//       if(ps>pe||is>ie){
//           return null;
//       }
//       Node node=new Node(pre[pe]);
//       int idx=is;
//       while(idx<=ie){
//           if(in[idx]==pre[pe]){
//           break;
//           }  
//       idx++;
//       }
//     int tne=idx-is;

//     node.left=postIn(pre,in,ps+1,ps+tne-1,is,idx-1);
//     node.right=postIn(pre,in,ps+tne,pe,idx+1,ie);

//     return node;
//   }

}