//package entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import helpers.PfmMainPageHelper;
//import lombok.Data;
//
//@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class Category {
//    protected String id;
//    protected String title;
//    protected Amount amount;
//    protected Amount baseAmount = new Amount();
//
//    public CommonCategory convertRestCategoryIntoCommonCategory() {
//        CommonCategory commonCategory = new CommonCategory();
//        commonCategory.setTitle(this.getTitle());
//        commonCategory.setStringAmount(PfmMainPageHelper.getAmountWithCurrencyWithoutFractionPart(this.getBaseAmount()));
//        return commonCategory;
//    }
//}