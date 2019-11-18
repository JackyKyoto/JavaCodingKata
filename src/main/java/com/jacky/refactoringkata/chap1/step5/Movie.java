package com.jacky.refactoringkata.chap1.step5;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title;
    private Price _price;

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public int getPriceCode() {
        return _price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        // TODO: 2019-07-23   这里 可以考虑工厂模式
        switch (arg) {
            case REGULAR:
                _price = new RegularPrice();
                break;
            case CHILDRENS:
                _price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException(" Incorrect Price Code");
        }
    }

    private int _priceCode;

    public Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

    double getCharge(int daysRented) {
       return _price.getCharge(daysRented);
    }

    int getFrequentRenterPoints(int daysRented) {
       return _price.getFrequentRenterPoints(daysRented);
    }

}
