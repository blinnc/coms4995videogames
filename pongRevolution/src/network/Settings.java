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

public class Settings implements TBase<Settings, Settings._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("Settings");

  private static final TField BALL_RADIUS_FIELD_DESC = new TField("ballRadius", TType.I32, (short)1);

  public int ballRadius;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    BALL_RADIUS((short)1, "ballRadius");

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
        case 1: // BALL_RADIUS
          return BALL_RADIUS;
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
  private static final int __BALLRADIUS_ISSET_ID = 0;
  private BitSet __isset_bit_vector = new BitSet(1);

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.BALL_RADIUS, new FieldMetaData("ballRadius", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(Settings.class, metaDataMap);
  }

  public Settings() {
  }

  public Settings(
    int ballRadius)
  {
    this();
    this.ballRadius = ballRadius;
    setBallRadiusIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Settings(Settings other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.ballRadius = other.ballRadius;
  }

  public Settings deepCopy() {
    return new Settings(this);
  }

  @Override
  public void clear() {
    setBallRadiusIsSet(false);
    this.ballRadius = 0;
  }

  public int getBallRadius() {
    return this.ballRadius;
  }

  public Settings setBallRadius(int ballRadius) {
    this.ballRadius = ballRadius;
    setBallRadiusIsSet(true);
    return this;
  }

  public void unsetBallRadius() {
    __isset_bit_vector.clear(__BALLRADIUS_ISSET_ID);
  }

  /** Returns true if field ballRadius is set (has been asigned a value) and false otherwise */
  public boolean isSetBallRadius() {
    return __isset_bit_vector.get(__BALLRADIUS_ISSET_ID);
  }

  public void setBallRadiusIsSet(boolean value) {
    __isset_bit_vector.set(__BALLRADIUS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case BALL_RADIUS:
      if (value == null) {
        unsetBallRadius();
      } else {
        setBallRadius((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case BALL_RADIUS:
      return new Integer(getBallRadius());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case BALL_RADIUS:
      return isSetBallRadius();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Settings)
      return this.equals((Settings)that);
    return false;
  }

  public boolean equals(Settings that) {
    if (that == null)
      return false;

    boolean this_present_ballRadius = true;
    boolean that_present_ballRadius = true;
    if (this_present_ballRadius || that_present_ballRadius) {
      if (!(this_present_ballRadius && that_present_ballRadius))
        return false;
      if (this.ballRadius != that.ballRadius)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(Settings other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    Settings typedOther = (Settings)other;

    lastComparison = Boolean.valueOf(isSetBallRadius()).compareTo(typedOther.isSetBallRadius());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBallRadius()) {
      lastComparison = TBaseHelper.compareTo(this.ballRadius, typedOther.ballRadius);
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
        case 1: // BALL_RADIUS
          if (field.type == TType.I32) {
            this.ballRadius = iprot.readI32();
            setBallRadiusIsSet(true);
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
    oprot.writeFieldBegin(BALL_RADIUS_FIELD_DESC);
    oprot.writeI32(this.ballRadius);
    oprot.writeFieldEnd();
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Settings(");
    boolean first = true;

    sb.append("ballRadius:");
    sb.append(this.ballRadius);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}
