/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package network;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum BallType implements TEnum {
  RED(1),
  BLUE(2),
  SPEED(3),
  INVIS(4),
  SHADOW(5),
  MAGNET(6),
  EXTEND(7),
  PUSH(8),
  LASER(9),
  WALL(10);

  private final int value;

  private BallType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static BallType findByValue(int value) { 
    switch (value) {
      case 1:
        return RED;
      case 2:
        return BLUE;
      case 3:
        return SPEED;
      case 4:
        return INVIS;
      case 5:
        return SHADOW;
      case 6:
        return MAGNET;
      case 7:
        return EXTEND;
      case 8:
        return PUSH;
      case 9:
        return LASER;
      case 10:
        return WALL;
      default:
        return null;
    }
  }
}