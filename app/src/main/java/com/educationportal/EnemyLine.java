package com.educationportal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Devaux on 18/11/15.
 */
public class EnemyLine {
    private final int colors[] = {Color.BLACK , Color.BLUE , Color.RED , Color.YELLOW , Color.GREEN , Color.GRAY } ;
    Enemy enemyLine[] ;
    int nbColor = 5 ;
    int nbForm = 3 ;
    int tenthWidth ;

    Random rd = new Random() ;

    int colorToShoot[] ;
    int formToShoot[] ;
    int indexToShoot ;
    public EnemyLine(int width, Context mcontext, int nbColor , int nbForm) {
        this.nbColor = nbColor ;
        this.nbForm = nbForm ;
        enemyLine = new Enemy[nbColor+1] ;
         tenthWidth = width / 10 ;
        for (int i = 1; i < nbColor+1; i++) {
            enemyLine[i] = new Enemy(colors[i] , mcontext , (1 + 2*i)*tenthWidth , 30);
        }
        enemyLine[0] = new Enemy(Color.BLACK , mcontext , tenthWidth , 30);
        indexToShoot = 0 ;
    }

    public void setX(Canvas canvas , Context mcontext) {
        for (int i = 0; i < nbColor + 1; i++) {
            enemyLine[i] = new Enemy(colors[i] , mcontext , canvas.getWidth()/(i+1) , 30);
        }
    }

    public void setBounds(int lx, int ly, int ux, int uy) {
        for (int i = 0; i < nbColor + 1; i++) {
            enemyLine[i].setBounds(lx , ly , ux , uy );
        }
    }

    public void update(int c) {
        nbColor = c;
    }

    public boolean move() {
        boolean res = true ;
        for (int i = 0; i < nbColor + 1 ; i++) {
            boolean acc = enemyLine[i].moveForLine() ;
            res = res && acc ;
        }
        if ( !res ) {
            indexToShoot = 0 ;
            reset();
        }
        return res ;
    }

    public void reset() {
        for (int i = 0; i < nbColor + 1 ; i++) {
            enemyLine[i].resetY();
        }
    }

    public void setForm () {
        for (int i = 0; i < nbColor + 1; i++) {
            enemyLine[i].setForm();
        }
    }

    public void resetForm () {
        for (int i = 0; i < nbColor + 1; i++) {
            enemyLine[i].form = (enemyLine[i].form + 1) %nbForm ;
        }
    }

    public void resetX () {
        for (int i = 0; i < nbColor + 1; i++) {
            enemyLine[i].x = (1 + 2*i)*50 ;
        }
    }

    public Enemy[] getEnemyLine() {return enemyLine;}

    public void draw(Canvas canvas) {
        for (int i = 1; i < nbColor + 1 ; i++) {
            enemyLine[i].draw(canvas);
        }
        Enemy next = enemyLine[0] ;
        Paint paint = new Paint();
        paint.setColor(next.getColor());
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(80);
        canvas.drawText("->" , next.x-40 , next.y +25 , paint);
    }

    public void setColorFormToShoot(int[] colors , int[]forms) {
        colorToShoot = colors ;
        formToShoot = forms ;
    }

    //when shoot wrong color return -1 when shot good color, if end of the serie of balls return 1
    //if have to continue return 0
    public int gooodShot(Enemy enemy) {
        int color = enemy.getColor();
        int form = enemy.getForm() ;
        if(color == colorToShoot[indexToShoot] && form == formToShoot[indexToShoot]) {
            indexToShoot++ ;
            if ( indexToShoot == colorToShoot.length) {
                indexToShoot = 0 ;
                return 1 ;
            } else {
                return 0 ;
            }
        } else if (color == Color.BLACK){
            if ( form == formToShoot[indexToShoot] ) {
                indexToShoot = 0 ;
                return -1 ;
            } else {
                return 2 ;
            }
        } else {
            indexToShoot = 0 ;
            return -1 ;
        }
    }
    public void setNb ( int nbColor , int nbForm ) {
        this.nbColor = nbColor ;
        this.nbForm = nbForm ;
    }
}
