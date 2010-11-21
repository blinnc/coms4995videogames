/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package network;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.async.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.transport.*;
import org.apache.thrift.protocol.*;

public class TGameState implements TBase<TGameState, TGameState._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("TGameState");

  private static final TField PADDLES_FIELD_DESC = new TField("paddles", TType.LIST, (short)1);
  private static final TField BALLS_FIELD_DESC = new TField("balls", TType.LIST, (short)2);
  private static final TField RED_SCORE_FIELD_DESC = new TField("redScore", TType.I32, (short)3);
  private static final TField BLUE_SCORE_FIELD_DESC = new TField("blueScore", TType.I32, (short)4);
  private static final TField IS_LASER_RED_FIELD_DESC = new TField("isLaserRed", TType.BOOL, (short)5);
  private static final TField IS_LASER_BLUE_FIELD_DESC = new TField("isLaserBlue", TType.BOOL, (short)6);
  private static final TField IS_WALL_FIELD_DESC = new TField("isWall", TType.BOOL, (short)7);
  private static final TField PLAYER_UP_FIELD_DESC = new TField("playerUp", TType.I32, (short)8);
  private static final TField ALLY_UP_FIELD_DESC = new TField("allyUp", TType.I32, (short)9);

  public List<TPaddle> paddles;
  public List<TBall> balls;
  public int redScore;
  public int blueScore;
  public boolean isLaserRed;
  public boolean isLaserBlue;
  public boolean isWall;
  /**
   * 
   * @see TPowerUp
   */
  public TPowerUp playerUp;
  /**
   * 
   * @see TPowerUp
   */
  public TPowerUp allyUp;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    PADDLES((short)1, "paddles"),
    BALLS((short)2, "balls"),
    RED_SCORE((short)3, "redScore"),
    BLUE_SCORE((short)4, "blueScore"),
    IS_LASER_RED((short)5, "isLaserRed"),
    IS_LASER_BLUE((short)6, "isLaserBlue"),
    IS_WALL((short)7, "isWall"),
    /**
     * 
     * @see TPowerUp
     */
    PLAYER_UP((short)8, "playerUp"),
    /**
     * 
     * @see TPowerUp
     */
    ALLY_UP((short)9, "allyUp");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // PADDLES
          return PADDLES;
        case 2: // BALLS
          return BALLS;
        case 3: // RED_SCORE
          return RED_SCORE;
        case 4: // BLUE_SCORE
          return BLUE_SCORE;
        case 5: // IS_LASER_RED
          return IS_LASER_RED;
        case 6: // IS_LASER_BLUE
          return IS_LASER_BLUE;
        case 7: // IS_WALL
          return IS_WALL;
        case 8: // PLAYER_UP
          return PLAYER_UP;
        case 9: // ALLY_UP
          return ALLY_UP;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __REDSCORE_ISSET_ID = 0;
  private static final int __BLUESCORE_ISSET_ID = 1;
  private static final int __ISLASERRED_ISSET_ID = 2;
  private static final int __ISLASERBLUE_ISSET_ID = 3;
  private static final int __ISWALL_ISSET_ID = 4;
  private BitSet __isset_bit_vector = new BitSet(5);

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PADDLES, new FieldMetaData("paddles", TFieldRequirementType.DEFAULT, 
        new ListMetaData(TType.LIST, 
            new StructMetaData(TType.STRUCT, TPaddle.class))));
    tmpMap.put(_Fields.BALLS, new FieldMetaData("balls", TFieldRequirementType.DEFAULT, 
        new ListMetaData(TType.LIST, 
            new StructMetaData(TType.STRUCT, TBall.class))));
    tmpMap.put(_Fields.RED_SCORE, new FieldMetaData("redScore", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    tmpMap.put(_Fields.BLUE_SCORE, new FieldMetaData("blueScore", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    tmpMap.put(_Fields.IS_LASER_RED, new FieldMetaData("isLaserRed", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.BOOL)));
    tmpMap.put(_Fields.IS_LASER_BLUE, new FieldMetaData("isLaserBlue", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.BOOL)));
    tmpMap.put(_Fields.IS_WALL, new FieldMetaData("isWall", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.BOOL)));
    tmpMap.put(_Fields.PLAYER_UP, new FieldMetaData("playerUp", TFieldRequirementType.DEFAULT, 
        new EnumMetaData(TType.ENUM, TPowerUp.class)));
    tmpMap.put(_Fields.ALLY_UP, new FieldMetaData("allyUp", TFieldRequirementType.DEFAULT, 
        new EnumMetaData(TType.ENUM, TPowerUp.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(TGameState.class, metaDataMap);
  }

  public TGameState() {
  }

  public TGameState(
    List<TPaddle> paddles,
    List<TBall> balls,
    int redScore,
    int blueScore,
    boolean isLaserRed,
    boolean isLaserBlue,
    boolean isWall,
    TPowerUp playerUp,
    TPowerUp allyUp)
  {
    this();
    this.paddles = paddles;
    this.balls = balls;
    this.redScore = redScore;
    setRedScoreIsSet(true);
    this.blueScore = blueScore;
    setBlueScoreIsSet(true);
    this.isLaserRed = isLaserRed;
    setIsLaserRedIsSet(true);
    this.isLaserBlue = isLaserBlue;
    setIsLaserBlueIsSet(true);
    this.isWall = isWall;
    setIsWallIsSet(true);
    this.playerUp = playerUp;
    this.allyUp = allyUp;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TGameState(TGameState other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetPaddles()) {
      List<TPaddle> __this__paddles = new ArrayList<TPaddle>();
      for (TPaddle other_element : other.paddles) {
        __this__paddles.add(new TPaddle(other_element));
      }
      this.paddles = __this__paddles;
    }
    if (other.isSetBalls()) {
      List<TBall> __this__balls = new ArrayList<TBall>();
      for (TBall other_element : other.balls) {
        __this__balls.add(new TBall(other_element));
      }
      this.balls = __this__balls;
    }
    this.redScore = other.redScore;
    this.blueScore = other.blueScore;
    this.isLaserRed = other.isLaserRed;
    this.isLaserBlue = other.isLaserBlue;
    this.isWall = other.isWall;
    if (other.isSetPlayerUp()) {
      this.playerUp = other.playerUp;
    }
    if (other.isSetAllyUp()) {
      this.allyUp = other.allyUp;
    }
  }

  public TGameState deepCopy() {
    return new TGameState(this);
  }

  @Override
  public void clear() {
    this.paddles = null;
    this.balls = null;
    setRedScoreIsSet(false);
    this.redScore = 0;
    setBlueScoreIsSet(false);
    this.blueScore = 0;
    setIsLaserRedIsSet(false);
    this.isLaserRed = false;
    setIsLaserBlueIsSet(false);
    this.isLaserBlue = false;
    setIsWallIsSet(false);
    this.isWall = false;
    this.playerUp = null;
    this.allyUp = null;
  }

  public int getPaddlesSize() {
    return (this.paddles == null) ? 0 : this.paddles.size();
  }

  public java.util.Iterator<TPaddle> getPaddlesIterator() {
    return (this.paddles == null) ? null : this.paddles.iterator();
  }

  public void addToPaddles(TPaddle elem) {
    if (this.paddles == null) {
      this.paddles = new ArrayList<TPaddle>();
    }
    this.paddles.add(elem);
  }

  public List<TPaddle> getPaddles() {
    return this.paddles;
  }

  public TGameState setPaddles(List<TPaddle> paddles) {
    this.paddles = paddles;
    return this;
  }

  public void unsetPaddles() {
    this.paddles = null;
  }

  /** Returns true if field paddles is set (has been asigned a value) and false otherwise */
  public boolean isSetPaddles() {
    return this.paddles != null;
  }

  public void setPaddlesIsSet(boolean value) {
    if (!value) {
      this.paddles = null;
    }
  }

  public int getBallsSize() {
    return (this.balls == null) ? 0 : this.balls.size();
  }

  public java.util.Iterator<TBall> getBallsIterator() {
    return (this.balls == null) ? null : this.balls.iterator();
  }

  public void addToBalls(TBall elem) {
    if (this.balls == null) {
      this.balls = new ArrayList<TBall>();
    }
    this.balls.add(elem);
  }

  public List<TBall> getBalls() {
    return this.balls;
  }

  public TGameState setBalls(List<TBall> balls) {
    this.balls = balls;
    return this;
  }

  public void unsetBalls() {
    this.balls = null;
  }

  /** Returns true if field balls is set (has been asigned a value) and false otherwise */
  public boolean isSetBalls() {
    return this.balls != null;
  }

  public void setBallsIsSet(boolean value) {
    if (!value) {
      this.balls = null;
    }
  }

  public int getRedScore() {
    return this.redScore;
  }

  public TGameState setRedScore(int redScore) {
    this.redScore = redScore;
    setRedScoreIsSet(true);
    return this;
  }

  public void unsetRedScore() {
    __isset_bit_vector.clear(__REDSCORE_ISSET_ID);
  }

  /** Returns true if field redScore is set (has been asigned a value) and false otherwise */
  public boolean isSetRedScore() {
    return __isset_bit_vector.get(__REDSCORE_ISSET_ID);
  }

  public void setRedScoreIsSet(boolean value) {
    __isset_bit_vector.set(__REDSCORE_ISSET_ID, value);
  }

  public int getBlueScore() {
    return this.blueScore;
  }

  public TGameState setBlueScore(int blueScore) {
    this.blueScore = blueScore;
    setBlueScoreIsSet(true);
    return this;
  }

  public void unsetBlueScore() {
    __isset_bit_vector.clear(__BLUESCORE_ISSET_ID);
  }

  /** Returns true if field blueScore is set (has been asigned a value) and false otherwise */
  public boolean isSetBlueScore() {
    return __isset_bit_vector.get(__BLUESCORE_ISSET_ID);
  }

  public void setBlueScoreIsSet(boolean value) {
    __isset_bit_vector.set(__BLUESCORE_ISSET_ID, value);
  }

  public boolean isIsLaserRed() {
    return this.isLaserRed;
  }

  public TGameState setIsLaserRed(boolean isLaserRed) {
    this.isLaserRed = isLaserRed;
    setIsLaserRedIsSet(true);
    return this;
  }

  public void unsetIsLaserRed() {
    __isset_bit_vector.clear(__ISLASERRED_ISSET_ID);
  }

  /** Returns true if field isLaserRed is set (has been asigned a value) and false otherwise */
  public boolean isSetIsLaserRed() {
    return __isset_bit_vector.get(__ISLASERRED_ISSET_ID);
  }

  public void setIsLaserRedIsSet(boolean value) {
    __isset_bit_vector.set(__ISLASERRED_ISSET_ID, value);
  }

  public boolean isIsLaserBlue() {
    return this.isLaserBlue;
  }

  public TGameState setIsLaserBlue(boolean isLaserBlue) {
    this.isLaserBlue = isLaserBlue;
    setIsLaserBlueIsSet(true);
    return this;
  }

  public void unsetIsLaserBlue() {
    __isset_bit_vector.clear(__ISLASERBLUE_ISSET_ID);
  }

  /** Returns true if field isLaserBlue is set (has been asigned a value) and false otherwise */
  public boolean isSetIsLaserBlue() {
    return __isset_bit_vector.get(__ISLASERBLUE_ISSET_ID);
  }

  public void setIsLaserBlueIsSet(boolean value) {
    __isset_bit_vector.set(__ISLASERBLUE_ISSET_ID, value);
  }

  public boolean isIsWall() {
    return this.isWall;
  }

  public TGameState setIsWall(boolean isWall) {
    this.isWall = isWall;
    setIsWallIsSet(true);
    return this;
  }

  public void unsetIsWall() {
    __isset_bit_vector.clear(__ISWALL_ISSET_ID);
  }

  /** Returns true if field isWall is set (has been asigned a value) and false otherwise */
  public boolean isSetIsWall() {
    return __isset_bit_vector.get(__ISWALL_ISSET_ID);
  }

  public void setIsWallIsSet(boolean value) {
    __isset_bit_vector.set(__ISWALL_ISSET_ID, value);
  }

  /**
   * 
   * @see TPowerUp
   */
  public TPowerUp getPlayerUp() {
    return this.playerUp;
  }

  /**
   * 
   * @see TPowerUp
   */
  public TGameState setPlayerUp(TPowerUp playerUp) {
    this.playerUp = playerUp;
    return this;
  }

  public void unsetPlayerUp() {
    this.playerUp = null;
  }

  /** Returns true if field playerUp is set (has been asigned a value) and false otherwise */
  public boolean isSetPlayerUp() {
    return this.playerUp != null;
  }

  public void setPlayerUpIsSet(boolean value) {
    if (!value) {
      this.playerUp = null;
    }
  }

  /**
   * 
   * @see TPowerUp
   */
  public TPowerUp getAllyUp() {
    return this.allyUp;
  }

  /**
   * 
   * @see TPowerUp
   */
  public TGameState setAllyUp(TPowerUp allyUp) {
    this.allyUp = allyUp;
    return this;
  }

  public void unsetAllyUp() {
    this.allyUp = null;
  }

  /** Returns true if field allyUp is set (has been asigned a value) and false otherwise */
  public boolean isSetAllyUp() {
    return this.allyUp != null;
  }

  public void setAllyUpIsSet(boolean value) {
    if (!value) {
      this.allyUp = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PADDLES:
      if (value == null) {
        unsetPaddles();
      } else {
        setPaddles((List<TPaddle>)value);
      }
      break;

    case BALLS:
      if (value == null) {
        unsetBalls();
      } else {
        setBalls((List<TBall>)value);
      }
      break;

    case RED_SCORE:
      if (value == null) {
        unsetRedScore();
      } else {
        setRedScore((Integer)value);
      }
      break;

    case BLUE_SCORE:
      if (value == null) {
        unsetBlueScore();
      } else {
        setBlueScore((Integer)value);
      }
      break;

    case IS_LASER_RED:
      if (value == null) {
        unsetIsLaserRed();
      } else {
        setIsLaserRed((Boolean)value);
      }
      break;

    case IS_LASER_BLUE:
      if (value == null) {
        unsetIsLaserBlue();
      } else {
        setIsLaserBlue((Boolean)value);
      }
      break;

    case IS_WALL:
      if (value == null) {
        unsetIsWall();
      } else {
        setIsWall((Boolean)value);
      }
      break;

    case PLAYER_UP:
      if (value == null) {
        unsetPlayerUp();
      } else {
        setPlayerUp((TPowerUp)value);
      }
      break;

    case ALLY_UP:
      if (value == null) {
        unsetAllyUp();
      } else {
        setAllyUp((TPowerUp)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PADDLES:
      return getPaddles();

    case BALLS:
      return getBalls();

    case RED_SCORE:
      return new Integer(getRedScore());

    case BLUE_SCORE:
      return new Integer(getBlueScore());

    case IS_LASER_RED:
      return new Boolean(isIsLaserRed());

    case IS_LASER_BLUE:
      return new Boolean(isIsLaserBlue());

    case IS_WALL:
      return new Boolean(isIsWall());

    case PLAYER_UP:
      return getPlayerUp();

    case ALLY_UP:
      return getAllyUp();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PADDLES:
      return isSetPaddles();
    case BALLS:
      return isSetBalls();
    case RED_SCORE:
      return isSetRedScore();
    case BLUE_SCORE:
      return isSetBlueScore();
    case IS_LASER_RED:
      return isSetIsLaserRed();
    case IS_LASER_BLUE:
      return isSetIsLaserBlue();
    case IS_WALL:
      return isSetIsWall();
    case PLAYER_UP:
      return isSetPlayerUp();
    case ALLY_UP:
      return isSetAllyUp();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TGameState)
      return this.equals((TGameState)that);
    return false;
  }

  public boolean equals(TGameState that) {
    if (that == null)
      return false;

    boolean this_present_paddles = true && this.isSetPaddles();
    boolean that_present_paddles = true && that.isSetPaddles();
    if (this_present_paddles || that_present_paddles) {
      if (!(this_present_paddles && that_present_paddles))
        return false;
      if (!this.paddles.equals(that.paddles))
        return false;
    }

    boolean this_present_balls = true && this.isSetBalls();
    boolean that_present_balls = true && that.isSetBalls();
    if (this_present_balls || that_present_balls) {
      if (!(this_present_balls && that_present_balls))
        return false;
      if (!this.balls.equals(that.balls))
        return false;
    }

    boolean this_present_redScore = true;
    boolean that_present_redScore = true;
    if (this_present_redScore || that_present_redScore) {
      if (!(this_present_redScore && that_present_redScore))
        return false;
      if (this.redScore != that.redScore)
        return false;
    }

    boolean this_present_blueScore = true;
    boolean that_present_blueScore = true;
    if (this_present_blueScore || that_present_blueScore) {
      if (!(this_present_blueScore && that_present_blueScore))
        return false;
      if (this.blueScore != that.blueScore)
        return false;
    }

    boolean this_present_isLaserRed = true;
    boolean that_present_isLaserRed = true;
    if (this_present_isLaserRed || that_present_isLaserRed) {
      if (!(this_present_isLaserRed && that_present_isLaserRed))
        return false;
      if (this.isLaserRed != that.isLaserRed)
        return false;
    }

    boolean this_present_isLaserBlue = true;
    boolean that_present_isLaserBlue = true;
    if (this_present_isLaserBlue || that_present_isLaserBlue) {
      if (!(this_present_isLaserBlue && that_present_isLaserBlue))
        return false;
      if (this.isLaserBlue != that.isLaserBlue)
        return false;
    }

    boolean this_present_isWall = true;
    boolean that_present_isWall = true;
    if (this_present_isWall || that_present_isWall) {
      if (!(this_present_isWall && that_present_isWall))
        return false;
      if (this.isWall != that.isWall)
        return false;
    }

    boolean this_present_playerUp = true && this.isSetPlayerUp();
    boolean that_present_playerUp = true && that.isSetPlayerUp();
    if (this_present_playerUp || that_present_playerUp) {
      if (!(this_present_playerUp && that_present_playerUp))
        return false;
      if (!this.playerUp.equals(that.playerUp))
        return false;
    }

    boolean this_present_allyUp = true && this.isSetAllyUp();
    boolean that_present_allyUp = true && that.isSetAllyUp();
    if (this_present_allyUp || that_present_allyUp) {
      if (!(this_present_allyUp && that_present_allyUp))
        return false;
      if (!this.allyUp.equals(that.allyUp))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(TGameState other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    TGameState typedOther = (TGameState)other;

    lastComparison = Boolean.valueOf(isSetPaddles()).compareTo(typedOther.isSetPaddles());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPaddles()) {
      lastComparison = TBaseHelper.compareTo(this.paddles, typedOther.paddles);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBalls()).compareTo(typedOther.isSetBalls());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBalls()) {
      lastComparison = TBaseHelper.compareTo(this.balls, typedOther.balls);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRedScore()).compareTo(typedOther.isSetRedScore());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRedScore()) {
      lastComparison = TBaseHelper.compareTo(this.redScore, typedOther.redScore);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBlueScore()).compareTo(typedOther.isSetBlueScore());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBlueScore()) {
      lastComparison = TBaseHelper.compareTo(this.blueScore, typedOther.blueScore);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIsLaserRed()).compareTo(typedOther.isSetIsLaserRed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsLaserRed()) {
      lastComparison = TBaseHelper.compareTo(this.isLaserRed, typedOther.isLaserRed);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIsLaserBlue()).compareTo(typedOther.isSetIsLaserBlue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsLaserBlue()) {
      lastComparison = TBaseHelper.compareTo(this.isLaserBlue, typedOther.isLaserBlue);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIsWall()).compareTo(typedOther.isSetIsWall());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsWall()) {
      lastComparison = TBaseHelper.compareTo(this.isWall, typedOther.isWall);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPlayerUp()).compareTo(typedOther.isSetPlayerUp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPlayerUp()) {
      lastComparison = TBaseHelper.compareTo(this.playerUp, typedOther.playerUp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAllyUp()).compareTo(typedOther.isSetAllyUp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAllyUp()) {
      lastComparison = TBaseHelper.compareTo(this.allyUp, typedOther.allyUp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // PADDLES
          if (field.type == TType.LIST) {
            {
              TList _list4 = iprot.readListBegin();
              this.paddles = new ArrayList<TPaddle>(_list4.size);
              for (int _i5 = 0; _i5 < _list4.size; ++_i5)
              {
                TPaddle _elem6;
                _elem6 = new TPaddle();
                _elem6.read(iprot);
                this.paddles.add(_elem6);
              }
              iprot.readListEnd();
            }
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // BALLS
          if (field.type == TType.LIST) {
            {
              TList _list7 = iprot.readListBegin();
              this.balls = new ArrayList<TBall>(_list7.size);
              for (int _i8 = 0; _i8 < _list7.size; ++_i8)
              {
                TBall _elem9;
                _elem9 = new TBall();
                _elem9.read(iprot);
                this.balls.add(_elem9);
              }
              iprot.readListEnd();
            }
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // RED_SCORE
          if (field.type == TType.I32) {
            this.redScore = iprot.readI32();
            setRedScoreIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // BLUE_SCORE
          if (field.type == TType.I32) {
            this.blueScore = iprot.readI32();
            setBlueScoreIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 5: // IS_LASER_RED
          if (field.type == TType.BOOL) {
            this.isLaserRed = iprot.readBool();
            setIsLaserRedIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 6: // IS_LASER_BLUE
          if (field.type == TType.BOOL) {
            this.isLaserBlue = iprot.readBool();
            setIsLaserBlueIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 7: // IS_WALL
          if (field.type == TType.BOOL) {
            this.isWall = iprot.readBool();
            setIsWallIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 8: // PLAYER_UP
          if (field.type == TType.I32) {
            this.playerUp = TPowerUp.findByValue(iprot.readI32());
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 9: // ALLY_UP
          if (field.type == TType.I32) {
            this.allyUp = TPowerUp.findByValue(iprot.readI32());
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.paddles != null) {
      oprot.writeFieldBegin(PADDLES_FIELD_DESC);
      {
        oprot.writeListBegin(new TList(TType.STRUCT, this.paddles.size()));
        for (TPaddle _iter10 : this.paddles)
        {
          _iter10.write(oprot);
        }
        oprot.writeListEnd();
      }
      oprot.writeFieldEnd();
    }
    if (this.balls != null) {
      oprot.writeFieldBegin(BALLS_FIELD_DESC);
      {
        oprot.writeListBegin(new TList(TType.STRUCT, this.balls.size()));
        for (TBall _iter11 : this.balls)
        {
          _iter11.write(oprot);
        }
        oprot.writeListEnd();
      }
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(RED_SCORE_FIELD_DESC);
    oprot.writeI32(this.redScore);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(BLUE_SCORE_FIELD_DESC);
    oprot.writeI32(this.blueScore);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(IS_LASER_RED_FIELD_DESC);
    oprot.writeBool(this.isLaserRed);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(IS_LASER_BLUE_FIELD_DESC);
    oprot.writeBool(this.isLaserBlue);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(IS_WALL_FIELD_DESC);
    oprot.writeBool(this.isWall);
    oprot.writeFieldEnd();
    if (this.playerUp != null) {
      oprot.writeFieldBegin(PLAYER_UP_FIELD_DESC);
      oprot.writeI32(this.playerUp.getValue());
      oprot.writeFieldEnd();
    }
    if (this.allyUp != null) {
      oprot.writeFieldBegin(ALLY_UP_FIELD_DESC);
      oprot.writeI32(this.allyUp.getValue());
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TGameState(");
    boolean first = true;

    sb.append("paddles:");
    if (this.paddles == null) {
      sb.append("null");
    } else {
      sb.append(this.paddles);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("balls:");
    if (this.balls == null) {
      sb.append("null");
    } else {
      sb.append(this.balls);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("redScore:");
    sb.append(this.redScore);
    first = false;
    if (!first) sb.append(", ");
    sb.append("blueScore:");
    sb.append(this.blueScore);
    first = false;
    if (!first) sb.append(", ");
    sb.append("isLaserRed:");
    sb.append(this.isLaserRed);
    first = false;
    if (!first) sb.append(", ");
    sb.append("isLaserBlue:");
    sb.append(this.isLaserBlue);
    first = false;
    if (!first) sb.append(", ");
    sb.append("isWall:");
    sb.append(this.isWall);
    first = false;
    if (!first) sb.append(", ");
    sb.append("playerUp:");
    if (this.playerUp == null) {
      sb.append("null");
    } else {
      sb.append(this.playerUp);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("allyUp:");
    if (this.allyUp == null) {
      sb.append("null");
    } else {
      sb.append(this.allyUp);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}

