package com.gzzhwl.core.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzzhwl.core.constant.DeviceType;
import com.gzzhwl.core.data.model.PushDevice;
import com.gzzhwl.core.support.service.PushDeviceService;

@Component
public class PushDeviceSupport {
	@Autowired
	private PushDeviceService pushDeviceService;
	private static PushDeviceSupport support;

	@PostConstruct
	public void init() {
		support = this;
		support.pushDeviceService = pushDeviceService;
	}

	/**
	 * 获取所有设备信息
	 */
	public static List<PushDevice> findAllDevice() {
		return support.pushDeviceService.findPushDevice();
	}

	/**
	 * 获取某一类型的所有设备信息
	 */
	public static List<PushDevice> findAllDeviceByType(final DeviceType deviceType) {
		List<PushDevice> allDevice = findAllDevice();
		if (CollectionUtils.isNotEmpty(allDevice)) {
			String code = deviceType.getCode();
			List<PushDevice> allTypeDevice = new ArrayList<PushDevice>();
			for (PushDevice pushDevice : allDevice) {
				String type = pushDevice.getType();
				if (StringUtils.equals(type, code)) {
					allTypeDevice.add(pushDevice);
				}
			}
			return allTypeDevice;
		}
		return Collections.emptyList();
	}

	/**
	 * 获取某一类型某一渠道的设备信息
	 */
	public static PushDevice getDevice(final DeviceType deviceType, final String channel) {
		List<PushDevice> allDevice = findAllDeviceByType(deviceType);
		if (CollectionUtils.isNotEmpty(allDevice)) {
			for (PushDevice pushDevice : allDevice) {
				String c = pushDevice.getChannel();
				if (StringUtils.equals(c, channel)) {
					return pushDevice;
				}
			}
		}
		return null;
	}

	/**
	 * 获取某一个指定设备信息
	 */
	public static PushDevice findDeviceById(final String deviceId) {
		List<PushDevice> allDevice = findAllDevice();
		if (CollectionUtils.isNotEmpty(allDevice)) {
			for (PushDevice pushDevice : allDevice) {
				String targetId = pushDevice.getDeviceId();
				if (StringUtils.equals(targetId, deviceId)) {
					return pushDevice;
				}
			}
		}
		return null;
	}
}
