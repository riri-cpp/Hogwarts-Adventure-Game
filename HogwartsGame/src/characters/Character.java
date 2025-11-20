package characters;

public abstract class Character {
    protected String name;
    protected String house;
    protected int socialLink;

    public Character(String name, String house){
        this.name = name;
        this.house = house;
        this.socialLink = 50;
    }

    public abstract void introduce();
    public abstract String reactToAction(String action);

    public void increaseFriendship(int amount){
        socialLink += amount;
        System.out.println(name + "'s friendship level has been increased to [" + amount + "]!");
    }

    public void decreaseFriendship(int amount){
        socialLink -= amount;
        System.out.println(name + "'s friendship level has been decreased to [" + amount + "]!");
    }

    public String getName(){
        return name;
    }

    public String getHouse(){
        return house;
    }

    public int getSocialLink(){
        return socialLink;
    }

    public boolean isFriend(){
        return socialLink >= 70;
    }

}
