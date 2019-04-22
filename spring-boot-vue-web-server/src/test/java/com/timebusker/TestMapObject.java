package com.timebusker;

/**
 * @DESC:TestMapObject
 * @author:timebusker
 * @date:2019/4/18
 */
public class TestMapObject {

    private String id;
    private String name;

    public void test(TestMapObject object) {
        System.err.println("--------------->" + object);
        object.setId(System.currentTimeMillis() + "__BB");
        object.setName(System.currentTimeMillis() + "__BB");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestMapObject{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}
