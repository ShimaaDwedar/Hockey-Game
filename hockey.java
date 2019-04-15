package eg.edu.alexu.csd.datastructure.iceHockey.cs36;
import java.awt.*;
import java.util.LinkedList;
public class hockey implements IPlayersFinder {
	public static int max (int[][] arr,int row) {
		int max=0;
		for (int i=0;i<arr[0].length;i++) {
			if (arr[row][i]>=max) {
				max=arr[row][i];
			}
		}
		return max;
	}
	public static int min (int[][] arr,int row) {
		int min=50;
		for (int i=0;i<arr[0].length;i++) {
			if (arr[row][i]<=min) {
				min=arr[row][i];
			}
		}
		return min;
	}
	public static int counter=0;	
	public static LinkedList diff (char[][] photos,int i,int j, char team,LinkedList index){
	
				if (photos[i][j]==team){
					counter++;
					photos[i][j]='*';
					index.add(i);
					index.add(j);
					
					if (j+1<photos[0].length) {
					diff (photos,i,j+1,team,index);}
					if (i+1<photos.length) {
					diff (photos,i+1,j,team,index);}
					if (i-1>-1) {
					diff (photos,i-1,j,team,index);}
					if (j-1>-1) {
					diff (photos,i,j-1,team,index);}
				}
				
		return index;
	}
	
	public  java.awt.Point[] findPlayers (String[] photo, int team, int threshold){
		if (photo.length==0) {
			return null;
		}
		char CHteam= Integer.toString(team).charAt(0);
		LinkedList <Integer> ind =new LinkedList<>();
		LinkedList <Integer> index =new LinkedList<>();
		char [][] photos= new char [photo.length][photo[0].length()];
		for (int k=0;k<photo.length;k++) {
			photos[k]=photo[k].toCharArray();
		}
		LinkedList<Integer> points = new LinkedList<Integer>();
		LinkedList<Point> list = new LinkedList<Point>();
		int g=0,n=0;
		for ( int i=0;i<photo.length;i++) {
			for (int j=0;j<photo[0].length();j++) {
				ind=diff (photos,i,j,CHteam,index);
				if (!ind.isEmpty()){
				int arr[][]=new int[2][ind.size()/2];
				if (counter*4>=threshold) {
					for (int k=0;k<ind.size()/2;k++) {
						arr[0][k]=ind.get(g);
						arr[1][k]=ind.get(g+1);
						g=g+2;
					}g=0;
			
					points.add(max(arr,1)+min(arr,1)+1);
					points.add(max(arr,0)+min(arr,0)+1);
				}
				}
				counter=0;
			ind.clear();
			}	
		}
		g=0;
		
		Point []a= new Point[points.size()/2];
		Point []b= new Point[points.size()/2];
		for (int k=0;k<points.size()/2;k++) {
			a[k]=new Point(points.get(g),points.get(g+1));
			g=g+2;
		}
		int min=1000000000,helpmin=-1,save=0;
		int i;
		for (int j=0;j<points.size()/2;j++) {
				for (i=0;i<points.size()/2;i++) {
			if (a[i].x<=min&&a[i].x!=-1) {
				min=a[i].x;
				helpmin=a[i].y;
				save=i;
			}
			}
			b[j]=new Point (min,helpmin);
			a[save].x=-1;
			min=1000000000;
		}int j,temp;
		for ( i=0;i<points.size()/2;i++) {
			for (j=1;j<points.size()/2;j++) {
		if (b[i].x==b[j].x) {
			if (b[i].y<b[j].y) {
				temp=b[i].y;
				b[i].y=b[j].y;
				b[j].y=temp;
			}
		}
		}
	}
		
		for (  i=0;i<points.size()/2;i++) {
			
			System.out.println( b[i]);
			}
		return a;
		
	}
}
