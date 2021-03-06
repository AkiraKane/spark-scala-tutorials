/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.pinecone.tutorials.spark4basic.fileFormat.avro;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Twitter extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7559509558814024104L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Twitter\",\"namespace\":\"com.pinecone.tutorials.spark4basic.fileFormat.avro\",\"fields\":[{\"name\":\"username\",\"type\":\"string\",\"doc\":\"Name of the user account on Twitter.com\"},{\"name\":\"tweet\",\"type\":\"string\",\"doc\":\"The content of the user's Twitter message\"},{\"name\":\"timestamp\",\"type\":\"long\",\"doc\":\"Unix epoch time in seconds\"}],\"doc:\":\"A basic schema for storing Twitter messages\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** Name of the user account on Twitter.com */
  @Deprecated public CharSequence username;
  /** The content of the user's Twitter message */
  @Deprecated public CharSequence tweet;
  /** Unix epoch time in seconds */
  @Deprecated public long timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Twitter() {}

  /**
   * All-args constructor.
   * @param username Name of the user account on Twitter.com
   * @param tweet The content of the user's Twitter message
   * @param timestamp Unix epoch time in seconds
   */
  public Twitter(CharSequence username, CharSequence tweet, Long timestamp) {
    this.username = username;
    this.tweet = tweet;
    this.timestamp = timestamp;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public Object get(int field$) {
    switch (field$) {
    case 0: return username;
    case 1: return tweet;
    case 2: return timestamp;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, Object value$) {
    switch (field$) {
    case 0: username = (CharSequence)value$; break;
    case 1: tweet = (CharSequence)value$; break;
    case 2: timestamp = (Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'username' field.
   * @return Name of the user account on Twitter.com
   */
  public CharSequence getUsername() {
    return username;
  }

  /**
   * Sets the value of the 'username' field.
   * Name of the user account on Twitter.com
   * @param value the value to set.
   */
  public void setUsername(CharSequence value) {
    this.username = value;
  }

  /**
   * Gets the value of the 'tweet' field.
   * @return The content of the user's Twitter message
   */
  public CharSequence getTweet() {
    return tweet;
  }

  /**
   * Sets the value of the 'tweet' field.
   * The content of the user's Twitter message
   * @param value the value to set.
   */
  public void setTweet(CharSequence value) {
    this.tweet = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return Unix epoch time in seconds
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'timestamp' field.
   * Unix epoch time in seconds
   * @param value the value to set.
   */
  public void setTimestamp(Long value) {
    this.timestamp = value;
  }

  /**
   * Creates a new Twitter RecordBuilder.
   * @return A new Twitter RecordBuilder
   */
  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Creates a new Twitter RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Twitter RecordBuilder
   */
  public static Builder newBuilder(Builder other) {
    return new Builder(other);
  }

  /**
   * Creates a new Twitter RecordBuilder by copying an existing Twitter instance.
   * @param other The existing instance to copy.
   * @return A new Twitter RecordBuilder
   */
  public static Builder newBuilder(Twitter other) {
    return new Builder(other);
  }

  /**
   * RecordBuilder for Twitter instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Twitter>
    implements org.apache.avro.data.RecordBuilder<Twitter> {

    /** Name of the user account on Twitter.com */
    private CharSequence username;
    /** The content of the user's Twitter message */
    private CharSequence tweet;
    /** Unix epoch time in seconds */
    private long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.username)) {
        this.username = data().deepCopy(fields()[0].schema(), other.username);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.tweet)) {
        this.tweet = data().deepCopy(fields()[1].schema(), other.tweet);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Twitter instance
     * @param other The existing instance to copy.
     */
    private Builder(Twitter other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.username)) {
        this.username = data().deepCopy(fields()[0].schema(), other.username);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.tweet)) {
        this.tweet = data().deepCopy(fields()[1].schema(), other.tweet);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'username' field.
      * Name of the user account on Twitter.com
      * @return The value.
      */
    public CharSequence getUsername() {
      return username;
    }

    /**
      * Sets the value of the 'username' field.
      * Name of the user account on Twitter.com
      * @param value The value of 'username'.
      * @return This builder.
      */
    public Builder setUsername(CharSequence value) {
      validate(fields()[0], value);
      this.username = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'username' field has been set.
      * Name of the user account on Twitter.com
      * @return True if the 'username' field has been set, false otherwise.
      */
    public boolean hasUsername() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'username' field.
      * Name of the user account on Twitter.com
      * @return This builder.
      */
    public Builder clearUsername() {
      username = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'tweet' field.
      * The content of the user's Twitter message
      * @return The value.
      */
    public CharSequence getTweet() {
      return tweet;
    }

    /**
      * Sets the value of the 'tweet' field.
      * The content of the user's Twitter message
      * @param value The value of 'tweet'.
      * @return This builder.
      */
    public Builder setTweet(CharSequence value) {
      validate(fields()[1], value);
      this.tweet = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'tweet' field has been set.
      * The content of the user's Twitter message
      * @return True if the 'tweet' field has been set, false otherwise.
      */
    public boolean hasTweet() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'tweet' field.
      * The content of the user's Twitter message
      * @return This builder.
      */
    public Builder clearTweet() {
      tweet = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * Unix epoch time in seconds
      * @return The value.
      */
    public Long getTimestamp() {
      return timestamp;
    }

    /**
      * Sets the value of the 'timestamp' field.
      * Unix epoch time in seconds
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public Builder setTimestamp(long value) {
      validate(fields()[2], value);
      this.timestamp = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * Unix epoch time in seconds
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * Unix epoch time in seconds
      * @return This builder.
      */
    public Builder clearTimestamp() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    public Twitter build() {
      try {
        Twitter record = new Twitter();
        record.username = fieldSetFlags()[0] ? this.username : (CharSequence) defaultValue(fields()[0]);
        record.tweet = fieldSetFlags()[1] ? this.tweet : (CharSequence) defaultValue(fields()[1]);
        record.timestamp = fieldSetFlags()[2] ? this.timestamp : (Long) defaultValue(fields()[2]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
