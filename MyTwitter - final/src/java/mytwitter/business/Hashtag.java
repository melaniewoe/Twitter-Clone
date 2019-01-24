/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.business;

/**
 *
 * @author melaniewoe
 */
public class Hashtag {
    private long hashtagID;
    private String hashtagText;
    private long hashtagCount;
   
    public Hashtag() {
        hashtagText = "";
    }
    
    public Hashtag(String fromString)
    {
        String[] data = fromString.replace("[", "").split(",");
        this.sethashtagText(data[0]);
        
    }

    public String gethashtagText()
    {
        return this.hashtagText;
    }
    
    public void sethashtagText(String hashtagText) {
        this.hashtagText = hashtagText;
    }
    
    
    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("[%s]", this.gethashtagText()));
      return sb.toString();
    }
}
