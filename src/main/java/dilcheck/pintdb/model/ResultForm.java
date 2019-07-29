package dilcheck.pintdb.model;

import com.google.common.base.MoreObjects;

public class ResultForm {
  private final boolean status;
  private final Object data;

  public ResultForm(Builder builder) {
    this.status = builder.status;
    this.data = builder.data;
  }

  public boolean isStatus() {
    return status;
  }

  public Object getData() {
    return data;
  }

  public static class Builder {
    private boolean status;
    private Object data;

    public Builder status(boolean status) {
      this.status = status;
      return this;
    }

    public Builder data(Object data) {
      this.data = data;
      return this;
    }

    public ResultForm build() {
      return new ResultForm(this);
    }
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("status", this.status).add("data", data).toString();
  }
}

