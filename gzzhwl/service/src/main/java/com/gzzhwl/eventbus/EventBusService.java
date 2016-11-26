package com.gzzhwl.eventbus;

import com.gzzhwl.eventbus.model.EventBusData;

public interface EventBusService {

	public void post(EventBusData post);

	public void asyncPost(EventBusData post);

}
