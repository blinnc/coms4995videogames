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

public class TCollision implements TBase<TCollision, TCollision._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("TCollision");

  private static final TField PLAYER_FIELD_DESC = new TField("player", TType.I32, (short)1);
  private static final TField BALL_COMBO_FIELD_DESC = new TField("ballCombo", TType.I32, (short)2);
  private static final TField ID_FIELD_DESC = new TField("id", TType.I32, (short)3);
  private static final TField DECAY_FIELD_DESC = new TField("decay", TType.I32, (short)4);

  /**
   * 
   * @see TPlayer
   */
  public TPlayer player;
  public int ballCombo;
  public int id;
  public int decay;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    /**
     * 
     * @see TPlayer
     */
    PLAYER((short)1, "player"),
    BALL_COMBO((short)2, "ballCombo"),
    ID((short)3, "id"),
    DECAY((short)4, "decay");

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
        case 1: // PLAYER
          return PLAYER;
        case 2: // BALL_COMBO
          return BALL_COMBO;
        case 3: // ID
          return ID;
        case 4: // DECAY
          return DECAY;
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
  private static final int __BALLCOMBO_ISSET_ID = 0;
  private static final int __ID_ISSET_ID = 1;
  private static final int __DECAY_ISSET_ID = 2;
  private BitSet __isset_bit_vector = new BitSet(3);

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PLAYER, new FieldMetaData("player", TFieldRequirementType.DEFAULT, 
        new EnumMetaData(TType.ENUM, TPlayer.class)));
    tmpMap.put(_Fields.BALL_COMBO, new FieldMetaData("ballCombo", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    tmpMap.put(_Fields.ID, new FieldMetaData("id", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    tmpMap.put(_Fields.DECAY, new FieldMetaData("decay", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(TCollision.class, metaDataMap);
  }

  public TCollision() {
  }

  public TCollision(
    TPlayer player,
    int ballCombo,
    int id,
    int decay)
  {
    this();
    this.player = player;
    this.ballCombo = ballCombo;
    setBallComboIsSet(true);
    this.id = id;
    setIdIsSet(true);
    this.decay = decay;
    setDecayIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TCollision(TCollision other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetPlayer()) {
      this.player = other.player;
    }
    this.ballCombo = other.ballCombo;
    this.id = other.id;
    this.decay = other.decay;
  }

  public TCollision deepCopy() {
    return new TCollision(this);
  }

  @Override
  public void clear() {
    this.player = null;
    setBallComboIsSet(false);
    this.ballCombo = 0;
    setIdIsSet(false);
    this.id = 0;
    setDecayIsSet(false);
    this.decay = 0;
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
  public TCollision setPlayer(TPlayer player) {
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

  public int getBallCombo() {
    return this.ballCombo;
  }

  public TCollision setBallCombo(int ballCombo) {
    this.ballCombo = ballCombo;
    setBallComboIsSet(true);
    return this;
  }

  public void unsetBallCombo() {
    __isset_bit_vector.clear(__BALLCOMBO_ISSET_ID);
  }

  /** Returns true if field ballCombo is set (has been asigned a value) and false otherwise */
  public boolean isSetBallCombo() {
    return __isset_bit_vector.get(__BALLCOMBO_ISSET_ID);
  }

  public void setBallComboIsSet(boolean value) {
    __isset_bit_vector.set(__BALLCOMBO_ISSET_ID, value);
  }

  public int getId() {
    return this.id;
  }

  public TCollision setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bit_vector.clear(__ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been asigned a value) and false otherwise */
  public boolean isSetId() {
    return __isset_bit_vector.get(__ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bit_vector.set(__ID_ISSET_ID, value);
  }

  public int getDecay() {
    return this.decay;
  }

  public TCollision setDecay(int decay) {
    this.decay = decay;
    setDecayIsSet(true);
    return this;
  }

  public void unsetDecay() {
    __isset_bit_vector.clear(__DECAY_ISSET_ID);
  }

  /** Returns true if field decay is set (has been asigned a value) and false otherwise */
  public boolean isSetDecay() {
    return __isset_bit_vector.get(__DECAY_ISSET_ID);
  }

  public void setDecayIsSet(boolean value) {
    __isset_bit_vector.set(__DECAY_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PLAYER:
      if (value == null) {
        unsetPlayer();
      } else {
        setPlayer((TPlayer)value);
      }
      break;

    case BALL_COMBO:
      if (value == null) {
        unsetBallCombo();
      } else {
        setBallCombo((Integer)value);
      }
      break;

    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case DECAY:
      if (value == null) {
        unsetDecay();
      } else {
        setDecay((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PLAYER:
      return getPlayer();

    case BALL_COMBO:
      return new Integer(getBallCombo());

    case ID:
      return new Integer(getId());

    case DECAY:
      return new Integer(getDecay());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PLAYER:
      return isSetPlayer();
    case BALL_COMBO:
      return isSetBallCombo();
    case ID:
      return isSetId();
    case DECAY:
      return isSetDecay();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TCollision)
      return this.equals((TCollision)that);
    return false;
  }

  public boolean equals(TCollision that) {
    if (that == null)
      return false;

    boolean this_present_player = true && this.isSetPlayer();
    boolean that_present_player = true && that.isSetPlayer();
    if (this_present_player || that_present_player) {
      if (!(this_present_player && that_present_player))
        return false;
      if (!this.player.equals(that.player))
        return false;
    }

    boolean this_present_ballCombo = true;
    boolean that_present_ballCombo = true;
    if (this_present_ballCombo || that_present_ballCombo) {
      if (!(this_present_ballCombo && that_present_ballCombo))
        return false;
      if (this.ballCombo != that.ballCombo)
        return false;
    }

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_decay = true;
    boolean that_present_decay = true;
    if (this_present_decay || that_present_decay) {
      if (!(this_present_decay && that_present_decay))
        return false;
      if (this.decay != that.decay)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(TCollision other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    TCollision typedOther = (TCollision)other;

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
    lastComparison = Boolean.valueOf(isSetBallCombo()).compareTo(typedOther.isSetBallCombo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBallCombo()) {
      lastComparison = TBaseHelper.compareTo(this.ballCombo, typedOther.ballCombo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetId()).compareTo(typedOther.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = TBaseHelper.compareTo(this.id, typedOther.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDecay()).compareTo(typedOther.isSetDecay());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDecay()) {
      lastComparison = TBaseHelper.compareTo(this.decay, typedOther.decay);
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
        case 1: // PLAYER
          if (field.type == TType.I32) {
            this.player = TPlayer.findByValue(iprot.readI32());
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // BALL_COMBO
          if (field.type == TType.I32) {
            this.ballCombo = iprot.readI32();
            setBallComboIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // ID
          if (field.type == TType.I32) {
            this.id = iprot.readI32();
            setIdIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // DECAY
          if (field.type == TType.I32) {
            this.decay = iprot.readI32();
            setDecayIsSet(true);
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
    if (this.player != null) {
      oprot.writeFieldBegin(PLAYER_FIELD_DESC);
      oprot.writeI32(this.player.getValue());
      oprot.writeFieldEnd();
    }
    oprot.writeFieldBegin(BALL_COMBO_FIELD_DESC);
    oprot.writeI32(this.ballCombo);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(ID_FIELD_DESC);
    oprot.writeI32(this.id);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(DECAY_FIELD_DESC);
    oprot.writeI32(this.decay);
    oprot.writeFieldEnd();
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TCollision(");
    boolean first = true;

    sb.append("player:");
    if (this.player == null) {
      sb.append("null");
    } else {
      sb.append(this.player);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("ballCombo:");
    sb.append(this.ballCombo);
    first = false;
    if (!first) sb.append(", ");
    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("decay:");
    sb.append(this.decay);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}

