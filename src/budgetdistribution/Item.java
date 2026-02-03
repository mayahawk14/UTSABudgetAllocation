package budgetdistribution;


import java.io.*;
import java.util.*;
/* Item
 *
 * Stores the information associated with an Item which is desired by a Department
 */
class Item
{
  String name;    /* name of this item */
  Double price;   /* price of this item */

  /*
   * Constructor to build a Item
   */
  public Item( String name, Double price ){
    this.name = name;
    this.price = price;
  }

  /* Debugging tool
   * Converts this Item to a string
   */		
  @Override
  public String toString() {
    return "{ " + name + ", " + price + " }";
  }
}
