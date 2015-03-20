/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Helper;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Nguyễn Chiến Thắng
 */
public class TimeSpan {
    long timeStart;
    
    //Khởi tạo lấy thời gian hiện tại
    public TimeSpan(){
        timeStart = Calendar.getInstance().getTime().getTime(); 
    }
    
    //Lấy khoảng thời gian tính từ ngày tạo
    public int getTimeSpanSeconds(){
        long now = Calendar.getInstance().getTime().getTime();
        long minus = now - timeStart;
        return (int)(minus/1000);
    }
    
    //Lấy khoản thời gian từ ngày tạo tính theo miliseconse
    public long getTimeSpanMiliseconds(){
        long now = Calendar.getInstance().getTime().getTime();
        long minus = now - timeStart;
        return minus;
    }
    
    //Reset lại thời gian
    public void reset(){
        timeStart = Calendar.getInstance().getTime().getTime(); 
    }
}
