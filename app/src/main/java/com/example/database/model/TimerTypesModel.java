package com.hciproject.makanapa.database.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;


@DatabaseTable(tableName="categories")
public class CategoryModel
{
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_IMAGE = "image";

	@DatabaseField(columnName=COLUMN_ID, generatedId=true) private long id;
	@DatabaseField(columnName=COLUMN_NAME) private String name;
	@DatabaseField(columnName=COLUMN_IMAGE) private String image;
	@ForeignCollectionField private ForeignCollection<RecipeModel> recipes; // one to many


	// empty constructor
	public CategoryModel()
	{
	}


	public long getId()
	{
		return id;
	}


	public void setId(long id)
	{
		this.id = id;
	}


	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getImage()
	{
		return image;
	}


	public void setImage(String image)
	{
		this.image = image;
	}


	public List<RecipeModel> getRecipes()
	{
		List<RecipeModel> list = new ArrayList<>();
		for(RecipeModel m : recipes)
		{
			list.add(m);
		}
		return list;
	}


	public void setRecipes(ForeignCollection<RecipeModel> recipes)
	{
		this.recipes = recipes;
	}
}
