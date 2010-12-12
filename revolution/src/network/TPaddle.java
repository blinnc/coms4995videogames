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

public class TPaddle implements TBase<TPaddle, TPaddle._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("TPaddle");

  private static final TField RADIUS_FIELD_DESC = new TField("radius", TType.DOUBLE, (short)1);
  private static final TField ANGLE_FIELD_DESC = new TField("angle", TType.DOUBLE, (short)2);
  private static final TField PLAYER_FIELD_DESC = new TField("player", TType.I32, (short)3);
  private static final TField STORE_FIELD_DESC = new TField("store", TType.STRUCT, (short)4);
  private static final TField USED_FIELD_DESC = new TField("used", TType.STRUCT, (short)5);

  public double radius;
  public double angle;
  /**
   * 
   * @see TPlayer
   */
  public TPlayer player;
  public TPower store;
  public TPower used;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    RADIUS((short)1, "radius"),
    ANGLE((short)2, "angle"),
    /**
     * 
     * @see TPlayer
     */
    PLAYER((short)3, "player"),
    STORE((short)4, "store"),
    USED((short)5, "used");

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
        case 1: // RADIUS
          return RADIUS;
        case 2: // ANGLE
          return ANGLE;
        case 3: // PLAYER
          return PLAYER;
        case 4: // STORE
          return STORE;
        case 5: // USED
          return USED;
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
  private static final int __RADIUS_ISSET_ID = 0;
  private static final int __ANGLE_ISSET_ID = 1;
  private BitSet __isset_bit_vector = new BitSet(2);

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RADIUS, new FieldMetaData("radius", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.DOUBLE)));
    tmpMap.put(_Fields.ANGLE, new FieldMetaData("angle", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.DOUBLE)));
    tmpMap.put(_Fields.PLAYER, new FieldMetaData("player", TFieldRequirementType.DEFAULT, 
        new EnumMetaData(TType.ENUM, TPlayer.class)));
    tmpMap.put(_Fields.STORE, new FieldMetaData("store", TFieldRequirementType.DEFAULT, 
        new StructMetaData(TType.STRUCT, TPower.class)));
    tmpMap.put(_Fields.USED, new FieldMetaData("used", TFieldRequirementType.DEFAULT, 
        new StructMetaData(TType.STRUCT, TPower.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(TPaddle.class, metaDataMap);
  }

  public TPaddle() {
  }

  public TPaddle(
    double radius,
    double angle,
    TPlayer player,
    TPower store,
    TPower used)
  {
    this();
    this.radius = radius;
    setRadiusIsSet(true);
    this.angle = angle;
    setAngleIsSet(true);
    this.player = player;
    this.store = store;
    this.used = used;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TPaddle(TPaddle other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.radius = other.radius;
    this.angle = other.angle;
    if (other.isSetPlayer()) {
      this.player = other.player;
    }
    if (other.isSetStore()) {
      this.store = new TPower(other.store);
    }
    if (other.isSetUsed()) {
      this.used = new TPower(other.used);
    }
  }

  public TPaddle deepCopy() {
    return new TPaddle(this);
  }

  @Override
  public void clear() {
    setRadiusIsSet(false);
    this.radius = 0.0;
    setAngleIsSet(false);
    this.angle = 0.0;
    this.player = null;
    this.store = null;
    this.used = null;
  }

  public double getRadius() {
    return this.radius;
  }

  public TPaddle setRadius(double radius) {
    this.radius = radius;
    setRadiusIsSet(true);
    return this;
  }

  public void unsetRadius() {
    __isset_bit_vector.clear(__RADIUS_ISSET_ID);
  }

  /** Returns true if field radius is set (has been asigned a value) and false otherwise */
  public boolean isSetRadius() {
    return __isset_bit_vector.get(__RADIUS_ISSET_ID);
  }

  public void setRadiusIsSet(boolean value) {
    __isset_bit_vector.set(__RADIUS_ISSET_ID, value);
  }

  public double getAngle() {
    return this.angle;
  }

  public TPaddle setAngle(double angle) {
    this.angle = angle;
    setAngleIsSet(true);
    return this;
  }

  public void unsetAngle() {
    __isset_bit_vector.clear(__ANGLE_ISSET_ID);
  }

  /** Returns true if field angle is set (has been asigned a value) and false otherwise */
  public boolean isSetAngle() {
    return __isset_bit_vector.get(__ANGLE_ISSET_ID);
  }

  public void setAngleIsSet(boolean value) {
    __isset_bit_vector.set(__ANGLE_ISSET_ID, value);
  }

  /**
   * 
   * @see TPlayer
   */
  public TPlayer getPlayer() {
    return this.player;
  }

  /**
   * 
   * @see TPlayer
   */
  public TPaddle setPlayer(TPlayer player) {
    this.player = player;
    return this;
  }

  public void unsetPlayer() {
    this.player = null;
  }

  /** Returns true if field player is set (has been asigned a value) and false otherwise */
  public boolean isSetPlayer() {
    return this.player != null;
  }

  public void setPlayerIsSet(boolean value) {
    if (!value) {
      this.player = null;
    }
  }

  public TPower getStore() {
    return this.store;
  }

  public TPaddle setStore(TPower store) {
    this.store = store;
    return this;
  }

  public void unsetStore() {
    this.store = null;
  }

  /** Returns true if field store is set (has been asigned a value) and false otherwise */
  public boolean isSetStore() {
    return this.store != null;
  }

  public void setStoreIsSet(boolean value) {
    if (!value) {
      this.store = null;
    }
  }

  public TPower getUsed() {
    return this.used;
  }

  public TPaddle setUsed(TPower used) {
    this.used = used;
    return this;
  }

  public void unsetUsed() {
    this.used = null;
  }

  /** Returns true if field used is set (has been asigned a value) and false otherwise */
  public boolean isSetUsed() {
    return this.used != null;
  }

  public void setUsedIsSet(boolean value) {
    if (!value) {
      this.used = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RADIUS:
      if (value == null) {
        unsetRadius();
      } else {
        setRadius((Double)value);
      }
      break;

    case ANGLE:
      if (value == null) {
        unsetAngle();
      } else {
        setAngle((Double)value);
      }
      break;

    case PLAYER:
      if (value == null) {
        unsetPlayer();
      } else {
        setPlayer((TPlayer)value);
      }
      break;

    case STORE:
      if (value == null) {
        unsetStore();
      } else {
        setStore((TPower)value);
      }
      break;

    case USED:
      if (value == null) {
        unsetUsed();
      } else {
        setUsed((TPower)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RADIUS:
      return new Double(getRadius());

    case ANGLE:
      return new Double(getAngle());

    case PLAYER:
      return getPlayer();

    case STORE:
      return getStore();

    case USED:
      return getUsed();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case RADIUS:
      return isSetRadius();
    case ANGLE:
      return isSetAngle();
    case PLAYER:
      return isSetPlayer();
    case STORE:
      return isSetStore();
    case USED:
      return isSetUsed();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TPaddle)
      return this.equals((TPaddle)that);
    return false;
  }

  public boolean equals(TPaddle that) {
    if (that == null)
      return false;

    boolean this_present_radius = true;
    boolean that_present_radius = true;
    if (this_present_radius || that_present_radius) {
      if (!(this_present_radius && that_present_radius))
        return false;
      if (this.radius != that.radius)
        return false;
    }

    boolean this_present_angle = true;
    boolean that_present_angle = true;
    if (this_present_angle || that_present_angle) {
      if (!(this_present_angle && that_present_angle))
        return false;
      if (this.angle != that.angle)
        return false;
    }

    boolean this_present_player = true && this.isSetPlayer();
    boolean that_present_player = true && that.isSetPlayer();
    if (this_present_player || that_present_player) {
      if (!(this_present_player && that_present_player))
        return false;
      if (!this.player.equals(that.player))
        return false;
    }

    boolean this_present_store = true && this.isSetStore();
    boolean that_present_store = true && that.isSetStore();
    if (this_present_store || that_present_store) {
      if (!(this_present_store && that_present_store))
        return false;
      if (!this.store.equals(that.store))
        return false;
    }

    boolean this_present_used = true && this.isSetUsed();
    boolean that_present_used = true && that.isSetUsed();
    if (this_present_used || that_present_used) {
      if (!(this_present_used && that_present_used))
        return false;
      if (!this.used.equals(that.used))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(TPaddle other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    TPaddle typedOther = (TPaddle)other;

    lastComparison = Boolean.valueOf(isSetRadius()).compareTo(typedOther.isSetRadius());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRadius()) {
      lastComparison = TBaseHelper.compareTo(this.radius, typedOther.radius);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAngle()).compareTo(typedOther.isSetAngle());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAngle()) {
      lastComparison = TBaseHelper.compareTo(this.angle, typedOther.angle);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPlayer()).compareTo(typedOther.isSetPlayer());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPlayer()) {
      lastComparison = TBaseHelper.compareTo(this.player, typedOther.player);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStore()).compareTo(typedOther.isSetStore());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStore()) {
      lastComparison = TBaseHelper.compareTo(this.store, typedOther.store);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUsed()).compareTo(typedOther.isSetUsed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUsed()) {
      lastComparison = TBaseHelper.compareTo(this.used, typedOther.used);
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
        case 1: // RADIUS
          if (field.type == TType.DOUBLE) {
            this.radius = iprot.readDouble();
            setRadiusIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // ANGLE
          if (field.type == TType.DOUBLE) {
            this.angle = iprot.readDouble();
            setAngleIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // PLAYER
          if (field.type == TType.I32) {
            this.player = TPlayer.findByValue(iprot.readI32());
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // STORE
          if (field.type == TType.STRUCT) {
            this.store = new TPower();
            this.store.read(iprot);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 5: // USED
          if (field.type == TType.STRUCT) {
            this.used = new TPower();
            this.used.read(iprot);
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
    oprot.writeFieldBegin(RADIUS_FIELD_DESC);
    oprot.writeDouble(this.radius);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(ANGLE_FIELD_DESC);
    oprot.writeDouble(this.angle);
    oprot.writeFieldEnd();
    if (this.player != null) {
      oprot.writeFieldBegin(PLAYER_FIELD_DESC);
      oprot.writeI32(this.player.getValue());
      oprot.writeFieldEnd();
    }
    if (this.store != null) {
      oprot.writeFieldBegin(STORE_FIELD_DESC);
      this.store.write(oprot);
      oprot.writeFieldEnd();
    }
    if (this.used != null) {
      oprot.writeFieldBegin(USED_FIELD_DESC);
      this.used.write(oprot);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TPaddle(");
    boolean first = true;

    sb.append("radius:");
    sb.append(this.radius);
    first = false;
    if (!first) sb.append(", ");
    sb.append("angle:");
    sb.append(this.angle);
    first = false;
    if (!first) sb.append(", ");
    sb.append("player:");
    if (this.player == null) {
      sb.append("null");
    } else {
      sb.append(this.player);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("store:");
    if (this.store == null) {
      sb.append("null");
    } else {
      sb.append(this.store);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("used:");
    if (this.used == null) {
      sb.append("null");
    } else {
      sb.append(this.used);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}
