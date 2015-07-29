package com.gemstone.gemfire.distributed.internal.membership.gms.locator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.gemstone.gemfire.DataSerializer;
import com.gemstone.gemfire.distributed.internal.membership.InternalDistributedMember;
import com.gemstone.gemfire.internal.DataSerializableFixedID;
import com.gemstone.gemfire.internal.Version;

public class FindCoordinatorResponse implements DataSerializableFixedID {
  private InternalDistributedMember coordinator;
  private boolean networkPartitionDetectionEnabled;
  private boolean usePreferredCoordinators;
  
  
  public FindCoordinatorResponse(InternalDistributedMember coordinator,
      boolean networkPartitionDectionEnabled, boolean usePreferredCoordinators) {
    this.coordinator = coordinator;
    this.networkPartitionDetectionEnabled = networkPartitionDectionEnabled;
    this.usePreferredCoordinators = usePreferredCoordinators;
  }
  
  public FindCoordinatorResponse() {
    // no-arg constructor for serialization
  }

  public boolean isNetworkPartitionDetectionEnabled() {
    return networkPartitionDetectionEnabled;
  }

  public boolean isUsePreferredCoordinators() {
    return usePreferredCoordinators;
  }

  public InternalDistributedMember getCoordinator() {
    return coordinator;
  }
  
  @Override
  public String toString() {
    return "FindCoordinatorResponse(coordinator="+coordinator+")";
  }



  @Override
  public Version[] getSerializationVersions() {
    return null;
  }

  @Override
  public int getDSFID() {
    return FIND_COORDINATOR_RESP;
  }

  @Override
  public void toData(DataOutput out) throws IOException {
    DataSerializer.writeObject(coordinator, out);
    out.writeBoolean(networkPartitionDetectionEnabled);
    out.writeBoolean(usePreferredCoordinators);
  }

  @Override
  public void fromData(DataInput in) throws IOException, ClassNotFoundException {
    coordinator = DataSerializer.readObject(in);
    networkPartitionDetectionEnabled = in.readBoolean();
    usePreferredCoordinators = in.readBoolean();
  }

}