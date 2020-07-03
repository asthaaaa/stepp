// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the speciic language governing permissions and
// limitations under the License.

package com.google.sps;
import java.util.*;
import java.util.Collection;

public final class FindMeetingQuery {
     public static class ORDER_BY_START implements Comparator<TimeRange>{

    public int compare(TimeRange a, TimeRange b) {
      return Long.compare(a.start(), b.start());
    }
  };
  public static ArrayList<TimeRange> merge(ArrayList<TimeRange> TimeRanges) {

        if(TimeRanges.size() == 0 || TimeRanges.size() == 1)
            return TimeRanges;


        TimeRange first = TimeRanges.get(0);
        int start = first.start();
        int end = first.end();

        ArrayList<TimeRange> result = new ArrayList<TimeRange>();

        for (int i = 1; i < TimeRanges.size(); i++) {
            TimeRange current = TimeRanges.get(i);
            if (current.start() <= end) {
                end = Math.max(current.end(), end);
            } else {
                result.add(TimeRange.fromStartDuration(start, end-start));
                start = current.start();
                end = current.end();
            }
        }

        result.add(TimeRange.fromStartDuration(start, end-start));
        return result;
    }
public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
      System.out.println("*");
 
    Collection<String> Attendee= request.getAttendees();
    Collection<String> op=request.getOptionalAttendees();
    System.out.println(op.size());
    ArrayList <String> Attendees=new ArrayList();
    for (String item: Attendee){Attendees.add(item);
    }
    

    for (String item: op){
Attendees.add(item);
 //System.out.println(op.size());
    }
   
    Collection <TimeRange> result=new ArrayList<>();
    ArrayList<TimeRange> busyy=new ArrayList<>();
    ArrayList<TimeRange> busy=new ArrayList<>();
    for (String item: Attendees){
        System.out.println(item);
    
 for (Event e:events){
     Set <String> ea=e.getAttendees();
     if(ea.contains(item)){


busy.add(e.getWhen());
       
     }
 }
    }
    System.out.println(request.getDuration());

    
       Collections.sort(busy,new ORDER_BY_START());
       int s=busy.size();


busyy=merge(busy);
       int st=TimeRange.START_OF_DAY;
int en=TimeRange.END_OF_DAY+1;
for(TimeRange ti:busyy){
    System.out.println(ti);
    TimeRange t=TimeRange.fromStartDuration(st,((ti).start())-st);
st=ti.end();
if(t.duration()>=request.getDuration()){
    result.add(t);
}
}
TimeRange t=TimeRange.fromStartDuration(st,en-(st));
if(t.duration()>request.getDuration()){
    result.add(t);
}
    


if(result.size()==0&&Attendee.size()!=0){
    return query1(events,request);
}
return result;
  }




  public Collection<TimeRange> query1(Collection<Event> events, MeetingRequest request) {
      System.out.println("*");
   // throw new UnsupportedOperationException("TODO: Implement this method.");
    Collection<String> Attendees= request.getAttendees();
   // HashMap<String, ArrayList<TimeRange>> calender=new HashMap<>();
    Collection <TimeRange> result=new ArrayList<>();
    ArrayList<TimeRange> busyy=new ArrayList<>();
    ArrayList<TimeRange> busy=new ArrayList<>();
    for (String item: Attendees){
        System.out.println(item);
    
 for (Event e:events){
     Set <String> ea=e.getAttendees();
     if(ea.contains(item)){


busy.add(e.getWhen());
       
     }
 }
    }
    System.out.println(request.getDuration());

    
       Collections.sort(busy,new ORDER_BY_START());
       int s=busy.size();


busyy=merge(busy);
       int st=TimeRange.START_OF_DAY;
int en=TimeRange.END_OF_DAY+1;
for(TimeRange ti:busyy){
    System.out.println(ti);
    TimeRange t=TimeRange.fromStartDuration(st,((ti).start())-st);
st=ti.end();
if(t.duration()>=request.getDuration()){
    result.add(t);
}
}
TimeRange t=TimeRange.fromStartDuration(st,en-(st));
if(t.duration()>request.getDuration()){
    result.add(t);
}
    


return result;

  }


}
