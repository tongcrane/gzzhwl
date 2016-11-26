<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${config.extdao}.${table.className}ExtDao">

<#assign str="#{">
<#assign end="}">
	<resultMap id="${table.varName}ResultMap" type="${table.className}">
<#list table.pkCloums as pk>
		<id property="${pk.propertyName}" column="${pk.cloumName}" />
</#list>
<#list table.cloums as c>
<#list table.pkCloums as pk>
<#if c.cloumName != pk.cloumName>
		<result property="${c.propertyName}" column="${c.cloumName}" />
</#if>
</#list>
</#list>
	</resultMap>
	<resultMap id="pageResultMap"  type="java.util.HashMap" extends="${table.varName}ResultMap"></resultMap>
	<resultMap id="findResultMap"  type="java.util.HashMap" extends="${table.varName}ResultMap"></resultMap>

	<insert id="insert" parameterType="${table.className}">
	   insert into ${table.tabelName} (
	    <#list table.cloums as c>
	     ${c.cloumName}<#if c_has_next>,</#if>
	</#list>
	   )values (
	    <#list table.cloums as c>
	     ${str}${c.propertyName}${end}<#if c_has_next>,</#if>
	</#list>
	   )
	</insert>

	<update id="update" parameterType="${table.className}">
	   update ${table.tabelName} set
	<#list table.cloums as c>
	     ${c.cloumName}=${str}${c.propertyName}${end}<#if c_has_next>,</#if>
	</#list>
	   where 
	<#list table.pkCloums as pk>
	   <#if pk_index!=0> and</#if> ${pk.cloumName}=${str}${pk.propertyName}${end} 
	   </#list>
	</update>
	
	<update id="updateSelective" parameterType="${table.className}">
	   update ${table.tabelName}
	   <trim prefix="set" suffixOverrides=",">
		   <#list table.cloums as c>
		     <if test="${c.propertyName} != null">
		       ${c.cloumName}=${str}${c.propertyName}${end}<#if c_has_next>,</#if>
		     </if>
			</#list>
	   </trim>
	
	   where 
	<#list table.pkCloums as pk>
	   <#if pk_index!=0> and</#if> ${pk.cloumName}=${str}${pk.propertyName}${end} 
	   </#list>
	</update>

	<delete id="delete" parameterType="map">
	   delete from 
	     ${table.tabelName} 
	   where 
	   <#list table.pkCloums as pk>
	   <#if pk_index!=0> and</#if> ${pk.cloumName}=${str}${pk.propertyName}${end} 
	   </#list>
	</delete>

	<select id="get" parameterType="map" resultMap="${table.varName}ResultMap">
	   select 
	<#list table.cloums as c>
	    ${c.cloumName}<#if c_has_next>,</#if>
	</#list>
	   from ${table.tabelName}
	   where 
	<#list table.pkCloums as pk>
	   <#if pk_index!=0> and</#if> ${pk.cloumName}=${str}${pk.propertyName}${end} 
	   </#list>
	</select>

	<select id="findOne" parameterType="map" resultMap="findResultMap">
	   select 
	<#list table.cloums as c>
	    ${c.cloumName}<#if c_has_next>,</#if>
	</#list>
	   from ${table.tabelName}
	   where 
	<#list table.pkCloums as pk>
	   <#if pk_index!=0> and</#if> ${pk.cloumName}=${str}${pk.propertyName}${end} 
	   </#list>
	</select>

	<select id="find" parameterType="map" resultMap="findResultMap">
	   select 
	<#list table.cloums as c>
	     ${c.cloumName}<#if c_has_next>,</#if>
	</#list>
	   from ${table.tabelName}
	   <trim prefix="WHERE" prefixOverrides="AND |OR ">
	<#list table.cloums as c>
	     <if test="${c.propertyName} != null">
	       <#if c_index!=0> and</#if> ${c.cloumName}=${str}${c.propertyName}${end}
	     </if>
	</#list>
	   </trim>
	</select>
	
	<select id="page" parameterType="map" resultMap="pageResultMap">
	   select 
	<#list table.cloums as c>
	     ${c.cloumName}<#if c_has_next>,</#if>
	</#list>
	   from ${table.tabelName}
	   <trim prefix="WHERE" prefixOverrides="AND |OR ">
	<#list table.cloums as c>
	     <if test="${c.propertyName} != null">
	       <#if c_index!=0> and</#if> ${c.cloumName}=${str}${c.propertyName}${end}
	     </if>
	</#list>
	   </trim>
	</select>
</mapper> 